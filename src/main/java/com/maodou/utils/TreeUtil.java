package com.maodou.utils;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Description: 树操作方法工具类
 * @Author: 公众号：赵侠客
 * @Copyright: Copyright (c) 赵侠客
 * @Date: 2024-07-22 10:42
 * @Version: 1.0
 */
public class TreeUtil {
    /**
     * 使用Map合成树
     *
     * @param menuList       需要合成树的List
     * @param pId            对象中的父ID字段,如:Menu:getPid
     * @param id             对象中的id字段 ,如：Menu:getId
     * @param rootCheck      判断E中为根节点的条件，如：x->x.getPId()==-1L , x->x.getParentId()==null,x->x.getParentMenuId()==0
     * @param setSubChildren E中设置下级数据方法，如： Menu::setSubMenus
     * @param <T>            ID字段类型
     * @param <E>            泛型实体对象
     * @return
     */
    public static <T, E> List<E> makeTree(List<E> menuList, Function<E, T> pId, Function<E, T> id, Predicate<E> rootCheck, BiConsumer<E, List<E>> setSubChildren) {
        //按原数组顺序构建父级数据Map，使用Optional考虑pId为null
        Map<Optional<T>, List<E>> parentMenuMap = menuList.stream().collect(Collectors.groupingBy(
                node -> Optional.ofNullable(pId.apply(node)),
                LinkedHashMap::new,
                Collectors.toList()
        ));
        List<E> result = new ArrayList<>();
        for (E node : menuList) {
            //添加到下级数据中
            setSubChildren.accept(node, parentMenuMap.get(Optional.ofNullable(id.apply(node))));
            //如里是根节点，加入结构
            if (rootCheck.test(node)) {
                result.add(node);
            }
        }
        return result;
    }

    /**
     * makeTree用例
     *
     * // 生成10万个测试数据
     * List<Menu> testMenus = generateTestMenus(100000);
     * // 构建树
     * List<Menu> tree = makeTree(testMenus,
     *      Menu::getParentId,
     *      Menu::getId,
     *      x -> x.getParentId() == 0,
     *      Menu::setSubMenus
     * );
     */

    /**
     * 树中过滤
     *
     * @param tree        需要过滤的树
     * @param predicate   过滤条件
     * @param getChildren 获取下级数据方法，如：MenuVo::getSubMenus
     * @param <E>         泛型实体对象
     * @return List<E> 过滤后的树
     */
    public static <E> List<E> filter(List<E> tree, Predicate<E> predicate, Function<E, List<E>> getChildren) {
        return tree.stream().filter(item -> {
            if (predicate.test(item)) {
                List<E> children = getChildren.apply(item);
                if (children != null && !children.isEmpty()) {
                    filter(children, predicate, getChildren);
                }
                return true;
            }
            return false;
        }).collect(Collectors.toList());
    }

    /**
     * filter用例
     *
     * //只保留id<5的节点
     * List<MenuVo> filterMenus =TreeUtil.filter(tree1,x->x.getId()<5,MenuVo::getSubMenus);
     */


    /**
     * 树中搜索
     *
     * @param tree
     * @param predicate
     * @param getSubChildren
     * @param <E>
     * @return 返回搜索到的节点及其父级到根节点
     */
    public static <E> List<E> search(List<E> tree, Predicate<E> predicate, Function<E, List<E>> getSubChildren) {
        Iterator<E> iterator = tree.iterator();
        while (iterator.hasNext()) {
            E item = iterator.next();
            List<E> childList = getSubChildren.apply(item);
            if (childList != null && !childList.isEmpty()) {
                search(childList, predicate, getSubChildren);
            }
            if (!predicate.test(item) && (childList == null || childList.isEmpty())) {
                iterator.remove();
            }
        }
        return tree;
    }

    /**
     * 对树形结构进行排序
     *
     * @param tree        要排序的树形结构，表示为节点列表。
     * @param comparator  用于节点比较的比较器。
     * @param getChildren 提供一种方法来获取每个节点的子节点列表。
     * @param <E>         元素的类型。
     * @return 排序后的节点列表。
     */
    public static <E> List<E> sort(List<E> tree, Comparator<? super E> comparator, Function<E, List<E>> getChildren) {
        // 对树的每个节点进行迭代处理
        for (E item : tree) {
            // 获取当前节点的子节点列表
            List<E> childList = getChildren.apply(item);
            // 如果子节点列表不为空，则递归调用 sort 方法对其进行排序
            if (childList != null && !childList.isEmpty()) {
                sort(childList, comparator, getChildren);
            }
        }
        // 对当前层级的节点列表进行排序
        // 这一步确保了所有直接子节点在递归返回后都已排序，然后对当前列表进行排序
        tree.sort(comparator);
        // 返回排序后的节点列表
        return tree;
    }

    /**
     * sort用例
     *
     * // 定义比较器（比如按 ID 排序）
     * Comparator<Menu> nodeComparator = Comparator.comparingInt(Menu::getId);
     * List<Menu> sort = sort(tree, nodeComparator, Menu::getSubMenus);
     */

    /**
     * 树中过滤并进行节点处理（此处是将节点的choose字段置为false，那么就在页面上可以展示但无法被勾选）
     *
     * @param tree        需要过滤的树
     * @param predicate   过滤条件
     * @param getChildren 获取下级数据方法，如：MenuVo::getSubMenus
     * @param setChoose   要被处理的字段，如：MenuVo::getChoose，可根据业务场景自行修改
     * @param <E>         泛型实体对象
     * @return List<E> 过滤后的树
     */
    public static <E> List<E> filterAndHandler(List<E> tree, Predicate<E> predicate, Function<E, List<E>> getChildren, BiConsumer<E, Boolean> setChoose) {
        return tree.stream().filter(item -> {
            //如果命中条件，则可以被勾选。（可根据业务场景自行修改）
            if (predicate.test(item)) {
                setChoose.accept(item, true);
            } else {
                setChoose.accept(item, false);
            }
            List<E> children = getChildren.apply(item);
            if (children != null && !children.isEmpty()) {
                filterAndHandler(children, predicate, getChildren, setChoose);
            }
            return true;
        }).collect(Collectors.toList());
    }

}

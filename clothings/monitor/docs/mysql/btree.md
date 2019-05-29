
# B-Tree 和 B+Tree #



## B-Tree

为了描述B-Tree，首先定义一个二元组[key,data], key为键值，对于不同的数据key是不相同的，data为除key之外的数据。那么B-Tree是满足下列条件的数据结构。

- d为大于1的正整数，称为B-Tree的度
- h为正整数，称为B-Tree的高度
- 每个非叶子节点由n-1个key和n个指针组成，其中  d<=n<=2d.
- 每个叶子节点最少包含一个key和2个指针，叶子节点的指针均为null。(待理解)
- 所有叶子节点具有相同的深度，等于树的高
- key和指针相互间隔，节点两端是指针。
- 一个节点中的key从左至右非递减排列。
- 所有节点组成树结构。
- 每个指针要么为null，要么指向另一个节点.
- 

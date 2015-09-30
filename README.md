<b><h3>Binary search tree</h3></b>

<i>In computer science, binary search trees (BST), sometimes called ordered or sorted binary trees, are a particular type of containers: data structures that store "items" (such as numbers, names and etc.) in memory. They allow fast lookup, addition and removal of items, and can be used to implement either dynamic sets of items, or lookup tables that allow finding an item by its key (e.g., finding the phone number of a person by name).

Binary search trees keep their keys in sorted order, so that lookup and other operations can use the principle of binary search: when looking for a key in a tree (or a place to insert a new key), they traverse the tree from root to leaf, making comparisons to keys stored in the nodes of the tree and deciding, based on the comparison, to continue searching in the left or right subtrees. On average, this means that each comparison allows the operations to skip about half of the tree, so that each lookup, insertion or deletion takes time proportional to the logarithm of the number of items stored in the tree. This is much better than the linear time required to find items by key in an (unsorted) array, but slower than the corresponding operations on hash tables.

They are a special case of the more general B-tree with order equal to two.</i>

<b><h3>Definition</h3></b>

<i>A binary search tree is a rooted binary tree, whose internal nodes each store a key (and optionally, an associated value) and each have two distinguished sub-trees, commonly denoted left and right. The tree additionally satisfies the binary search tree property, which states that the key in each node must be greater than all keys stored in the left sub-tree, and smaller than all keys in right sub-tree.[1] (The leaves (final nodes) of the tree contain no key and have no structure to distinguish them from one another. Leaves are commonly represented by a special leaf or nil symbol, a NULL pointer, etc.)

Generally, the information represented by each node is a record rather than a single data element. However, for sequencing purposes, nodes are compared according to their keys rather than any part of their associated records.

The major advantage of binary search trees over other data structures is that the related sorting algorithms and search algorithms such as in-order traversal can be very efficient; they are also easy to code.

Binary search trees are a fundamental data structure used to construct more abstract data structures such as sets, multisets, and associative arrays. Some of their disadvantages are as follows:

<ol><li>The shape of the binary search tree totally depends on the order of insertions, and it can be degenerated.</li>
<li>When inserting or searching for an element in binary search tree, the key of each visited node has to be compared with the key of the element to be inserted or found, i.e., it takes a long time to search an element in a binary search tree.</li>
<li>The keys in the binary search tree may be long and the run time may increase.</li>
<li>After a long intermixed sequence of random insertion and deletion, the expected height of the tree approaches square root of the number of keys, √n, which grows much faster than log n.</li></ol></i>

<b><h3>Operations</h3></b>

<i>Binary search trees support three main operations: insertion of keys, deletion of keys, and lookup (checking whether a key is present). Each requires a comparator, a subroutine that computes the total order (linear order) on any two keys. This comparator can be explicitly or implicitly defined, depending on the language in which the binary search tree was implemented. Comparators are usually less-than (<) or greater-than (>) functions: for instance, a < b or a > b, where a and b are keys of two nodes.<i>

<b><h3>Searching</h3></b>

<i>Searching a binary search tree for a specific key can be a recursive or an iterative process.

We begin by examining the root node. If the tree is null, the key we are searching for does not exist in the tree. Otherwise, if the key equals that of the root, the search is successful and we return the node. If the key is less than that of the root, we search the left subtree. Similarly, if the key is greater than that of the root, we search the right subtree. This process is repeated until the key is found or the remaining subtree is null. If the searched key is not found before a null subtree is reached, then the item must not be present in the tree. This is easily expressed as a recursive algorithm:</i>
<div width="500px" height="300px" bacground="#000" color="#fff">
 &nbsp;def search_recursively(key, node):</br>
    &nbsp;&nbsp; &nbsp;&nbsp;if node is None or node.key == key:</br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return node</br>
    &nbsp;&nbsp;&nbsp;&nbsp;elif key < node.key:</br>
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return search_recursively(key, node.left)</br>
    &nbsp;&nbsp;&nbsp;&nbsp;else:  # key > node.key</br>
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;return search_recursively(key, node.right)</br>
     &nbsp;&nbsp;&nbsp;&nbsp;return None</br>
<div>

#include <iostream>
#include <string>
#include <stdlib.h>

using namespace std;

class AVLnode
{
public:
	AVLnode(int value);
	int getValue();
	void setValue(int value);
	AVLnode* getLeft();
	AVLnode* getRight();
	void setLeft(AVLnode* node);
	void setRight(AVLnode* node);
	~AVLnode();

private:
	int value;
	AVLnode* left;
	AVLnode* right;
};

AVLnode::AVLnode(int value)
{
	this->value = value;
	left = NULL;
	right = NULL;
}

int AVLnode::getValue()
{
	return this->value;
}

void AVLnode::setValue(int value)
{
	this->value = value;
}

AVLnode * AVLnode::getLeft()
{
	return this->left;
}

AVLnode * AVLnode::getRight()
{
	return this->right;
}

void AVLnode::setLeft(AVLnode* node)
{
	this->left = node;
}

void AVLnode::setRight(AVLnode* node)
{
	this->right = node;
}

AVLnode::~AVLnode()
{
}

class AVLtree
{
public:
	AVLtree();
	AVLtree(AVLnode* root);
	AVLnode* find(AVLnode* node, int value);
	int getHeight(AVLnode* node);
	void insertNode(AVLnode* node);
	void removeNode(int value);
	void print(int opt);
	void cleanTree();
	~AVLtree();

private:
	AVLnode * root;
	int heightDiff(AVLnode* node);
	AVLnode * insert(AVLnode* mid, AVLnode* node);
	AVLnode* remove(AVLnode* node, int value);
	AVLnode* findCandidate(AVLnode* node);
	void printPreorder(AVLnode* node);
	void printInorder(AVLnode* node);
	void printPostorder(AVLnode* node);
	int max(int lhs, int rhs);
	void freeNodes(AVLnode* node);
};

AVLtree::AVLtree()
{
	this->root = NULL;
}

AVLtree::AVLtree(AVLnode* root)
{
	this->root = root;
}

AVLnode * AVLtree::find(AVLnode* node, int value)
{
	if (value == node->getValue())
	{
		return node;
	}
	if (value > node->getValue())
	{
		if (node->getRight() != NULL)
		{
			return find(node->getRight(), value);
		}
		else
		{
			return node;
		}
	}
	else
	{
		if (node->getLeft() != NULL)
		{
			return find(node->getLeft(), value);
		}
		else
		{
			return node;
		}
	}
}

void AVLtree::insertNode(AVLnode* node)
{
	root = insert(this->root, node);
}

void AVLtree::removeNode(int value)
{
	root = remove(this->root, value);
}

void AVLtree::print(int opt)
{
	if (this->root == NULL)
	{
		cout << "EMPTY" << endl;
		return;
	}
	switch (opt)
	{
	case 1:
		printPreorder(root);
		cout << endl;
		break;
	case 2:
		printInorder(root);
		cout << endl;
		break;
	case 3:
		printPostorder(root);
		cout << endl;
		break;
	}
}

void AVLtree::cleanTree()
{
	freeNodes(this->root);
	this->root = NULL;
}

int AVLtree::getHeight(AVLnode* node)
{
	if (node == NULL)
	{
		return -1;
	}
	if (node->getLeft() == NULL && node->getRight() == NULL)
	{
		return 0;
	}
	else if (node->getLeft() != NULL && node->getRight() != NULL)
	{
		return max(getHeight(node->getLeft()), getHeight(node->getRight())) + 1;

	}
	else if (node->getLeft() != NULL)
	{
		return getHeight(node->getLeft()) + 1;
	}
	else
	{
		return getHeight(node->getRight()) + 1;
	}
}

int AVLtree::heightDiff(AVLnode* node)
{
	int leftHeight = 0;
	int rightHeight = 0;
	if (node->getLeft() == NULL)
	{
		leftHeight = -1;
	}
	else
	{
		leftHeight = getHeight(node->getLeft());
	}
	if (node->getRight() == NULL)
	{
		rightHeight = -1;
	}
	else
	{
		rightHeight = getHeight(node->getRight());
	}
	return abs(leftHeight - rightHeight);
}

AVLnode* AVLtree::insert(AVLnode* current, AVLnode* node)
{
	if (current == NULL)
	{
		return node;
	}
	AVLnode* child = NULL;
	if (node->getValue() > current->getValue())
	{
		child = insert(current->getRight(), node);
		current->setRight(child);
	}
	else if (node->getValue() < current->getValue())
	{
		child = insert(current->getLeft(), node);
		current->setLeft(child);
	}
	int diff = heightDiff(current);
	if (abs(diff) > 1)
	{
		if (current->getValue() > child->getValue())
		{
			if (child->getValue() > node->getValue())
			{
				current->setLeft(child->getRight());
				child->setRight(current);
				return child;
			}
			else
			{
				AVLnode * mid = child->getRight();
				current->setLeft(mid->getRight());
				child->setRight(mid->getLeft());
				mid->setRight(current);
				mid->setLeft(child);
				return mid;
			}
		}
		else
		{
			if (child->getValue() > node->getValue())
			{
				AVLnode * mid = child->getLeft();
				current->setRight(mid->getLeft());
				child->setLeft(mid->getRight());
				mid->setLeft(current);
				mid->setRight(child);
				return mid;
			}
			else
			{
				current->setRight(child->getLeft());
				child->setLeft(current);
				return child;
			}
		}
	}
	return current;
}

AVLnode* AVLtree::remove(AVLnode* node, int value)
{
	if (node == NULL)
	{
		return NULL;
	}
	AVLnode * temp = NULL;
	if (value > node->getValue())
	{
		node->setRight(remove(node->getRight(), value));
		temp = node;
	}
	else if (value < node->getValue())
	{
		node->setLeft(remove(node->getLeft(), value));
		temp = node;
	}
	else if (node->getLeft() == NULL || node->getRight() == NULL)
	{
		if (node->getLeft() == NULL)
		{
			temp = node->getRight();
		}
		else
		{
			temp = node->getLeft();
		}
	}
	else
	{
		AVLnode * candidate = findCandidate(node);
		node->setValue(candidate->getValue());
		node->setLeft(remove(node->getLeft(), candidate->getValue()));
		temp = node;
	}
	if (temp != NULL)
	{
		AVLnode* lt = temp->getLeft();
		AVLnode* rt = temp->getRight();
		int leftHeight = getHeight(lt);
		int rightHeight = getHeight(rt);

		int lth = -1;
		int rth = -1;
		if (rightHeight - leftHeight > 1)
		{

			lth = getHeight(rt->getLeft());
			rth = getHeight(rt->getRight());

			if (lth <= rth)
			{
				temp->setRight(rt->getLeft());
				rt->setLeft(temp);
				temp = rt;
			}
			else
			{
				AVLnode* mid = rt->getLeft();
				temp->setRight(mid->getLeft());
				rt->setLeft(mid->getRight());
				mid->setLeft(temp);
				mid->setRight(rt);
				temp = mid;
			}
		}
		else if (leftHeight - rightHeight > 1)
		{

			lth = getHeight(lt->getLeft());
			rth = getHeight(lt->getRight());

			if (lth >= rth)
			{
				node->setLeft(lt->getRight());
				lt->setRight(node);
				temp = lt;
			}
			else
			{
				AVLnode* mid = lt->getRight();
				node->setLeft(mid->getRight());
				lt->setRight(mid->getLeft());
				mid->setLeft(lt);
				mid->setRight(node);
				temp = mid;
			}
		}
	}
	return temp;
}

AVLnode * AVLtree::findCandidate(AVLnode * node)
{
	AVLnode* candidate = node->getLeft();
	while (candidate->getRight() != NULL)
	{
		candidate = candidate->getRight();
	}
	return candidate;
}

int AVLtree::max(int lhs, int rhs)
{
	if (lhs >= rhs)
	{
		return lhs;
	}
	else
	{
		return rhs;
	}
}

void AVLtree::freeNodes(AVLnode * node)
{
	if (node->getLeft() != NULL)
	{
		freeNodes(node->getLeft());
	}
	if (node->getRight() != NULL)
	{
		freeNodes(node->getRight());
	}
	free(node);
}

void AVLtree::printPreorder(AVLnode* node)
{
	cout << node->getValue() << " ";
	if (node->getLeft() != NULL)
	{
		printPreorder(node->getLeft());
	}
	if (node->getRight() != NULL)
	{
		printPreorder(node->getRight());
	}
}

void AVLtree::printInorder(AVLnode* node)
{
	if (node->getLeft() != NULL)
	{
		printInorder(node->getLeft());
	}
	cout << node->getValue() << " ";
	if (node->getRight() != NULL)
	{
		printInorder(node->getRight());
	}
}

void AVLtree::printPostorder(AVLnode* node)
{
	if (node->getLeft() != NULL)
	{
		printPostorder(node->getLeft());
	}
	if (node->getRight() != NULL)
	{
		printPostorder(node->getRight());
	}
	cout << node->getValue() << " ";
}

AVLtree::~AVLtree()
{
}

/*bool traversal(AVLnode* node, AVLtree t)
{
	if (node == NULL)
	{
		return false;
	}
	bool a = false;
	bool b = false;
	bool c = false;
	if (node->getLeft() != NULL)
	{
		a = traversal(node->getLeft(), t);
	}
	b = t.heightDiff(node) > 1;
	if (node->getRight() != NULL)
	{
		c = traversal(node->getRight(), t);
	}
	return a || b || c;
}*/

int main()
{
	string input;
	AVLtree t;
	while (cin >> input)
	{
		if (input.at(0) == 'A')
		{
			int value = stoi(input.substr(1));
			AVLnode* node = new AVLnode(value);
			t.insertNode(node);
		}
		else if (input.at(0) == 'D')
		{
			int value = stoi(input.substr(1));
			t.removeNode(value);
		}
		else if (input.compare("PRE") == 0)
		{
			t.print(1);
			t.cleanTree();
		}
		else if (input.compare("IN") == 0)
		{
			t.print(2);
			t.cleanTree();
		}
		else if (input.compare("POST") == 0)
		{
			t.print(3);
			t.cleanTree();
		}
	}
	/*
	AVLnode* n0 = new AVLnode(1);
	AVLnode* n1 = new AVLnode(22);
	AVLnode* n2 = new AVLnode(3);
	AVLnode* n3 = new AVLnode(8);
	AVLnode* n4 = new AVLnode(9);
	AVLnode* n5 = new AVLnode(3);
	AVLnode* n6 = new AVLnode(7);
	AVLnode* n7 = new AVLnode(4);
	AVLnode* n8 = new AVLnode(10);



	AVLtree t1;
	t1.insertNode(n0);
	t1.insertNode(n1);
	t1.insertNode(n2);
	t1.insertNode(n3);
	t1.insertNode(n4);
	t1.insertNode(n5);
	t1.insertNode(n6);
	t1.insertNode(n7);
	t1.insertNode(n8);

	t1.print(1);
	t1.print(2);
	t1.print(3);

	t1.removeNode(7);

	*/

	return 0;
}



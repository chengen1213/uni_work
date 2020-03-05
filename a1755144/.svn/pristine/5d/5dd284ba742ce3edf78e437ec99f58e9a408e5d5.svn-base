#include <iostream>
#include <string>
#include <vector>
#define tableentries 26;

using namespace std;

class hashTable
{
	char keySet[26];
	string table[26];
	int status[26];
	//1:never used 2:tombstone 3:occupied
public:
	hashTable();
	~hashTable();
	int mapIndex(string key);
	int search(string key);
	void insert(string key);
	void remove(string key);
	void print();

private:

};

hashTable::hashTable()
{
	for (int i = 0; i < 26; ++i)
	{
		this->keySet[i] = 'a' + i;
		this->table[i] = "";
		this->status[i] = 1;
	}
}

hashTable::~hashTable()
{
}

int hashTable::mapIndex(string key)
{
	int index = -1;
	char last = key.at(key.length() - 1);
	for (int i = 0; i < 26; ++i)
	{
		if (last == keySet[i])
		{
			index = i;
			break;
		}
	}
	return index;
}

int hashTable::search(string key)
{
	int index = mapIndex(key);
	if (index != -1)
	{
		for (int i = 0; i < 26; ++i)
		{
			if (table[index] == key && status[index] == 3)
			{
				return index;
			}
			++index;
			if (index > 25)
			{
				index = 0;
			}
		}
	}
	return -1;
}

void hashTable::insert(string key)
{
	int index = search(key);
	if (index == -1)
	{
		index = mapIndex(key);
		for (int i = 0; i < 26; ++i)
		{
			if (status[index] == 1 || status[index] == 2)
			{
				status[index] = 3;
				table[index] = key;
				return;
			}
			else
			{
				++index;
				if (index > 25)
				{
					index = 0;
				}
			}
		}
	}
}

void hashTable::remove(string key)
{
	int index = search(key);
	if (index != -1)
	{
		status[index] = 2;
	}
}

void hashTable::print()
{
	for (int i = 0; i < 26; ++i)
	{
		if (status[i] == 3)
			cout << table[i] << " ";
	}
}

int main()
{
	string input;
	hashTable hash = hashTable();
	while (cin >> input)
	{
		if (input.at(0) == 'A')
		{
			hash.insert(input.substr(1));
		}
		else if (input.at(0) == 'D')
		{
			hash.remove(input.substr(1));
		}
	}
	hash.print();
	return 0;
}

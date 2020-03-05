#include <iostream>
#include <string>
#include <stdlib.h>
#include <vector>


using namespace std;

class MyNumber
{
public:
	MyNumber(int digits, int base);
	MyNumber(string digits, int base);
	MyNumber(vector<int> digits, int base);
	~MyNumber();
	vector<int> getDigits();
	int getBase();
	int getDigitWid();
	void normalize();
	MyNumber operator-();
	MyNumber schoolAdd(MyNumber rhs);
	MyNumber schoolSub(MyNumber rhs);
	MyNumber karatsubaMult(MyNumber rhs);
	MyNumber divide(MyNumber rhs);
	void shift(int position);
	void print();

private:
	int base;
	int digitWid;
	std::vector<int> digits;
};

bool operator>(MyNumber lNum, MyNumber rNum)
{
	vector<int> lDigits = lNum.getDigits();
	vector<int> rDigits = rNum.getDigits();
	int lSize = lDigits.size();
	int rSize = rDigits.size();
	if (lSize > rSize)
	{
		return true;
	}
	else if (lSize < rSize)
	{
		return false;
	}
	for (int i(lSize - 1); i >= 0; --i) {
		if (rDigits.at(i) > lDigits.at(i))
		{
			return false;
		}
		else if (lDigits.at(i) > rDigits.at(i))
		{
			return true;
		}
	}
	return false;
}

bool operator<(MyNumber lNum, MyNumber rNum)
{
	if (rNum > lNum)
	{
		return true;
	}
	else
	{
		return false;
	}
}

bool operator==(MyNumber lNum, MyNumber rNum)
{
	if (lNum > rNum || lNum < rNum)
	{
		return false;
	}
	else
	{
		return true;
	}
}

MyNumber operator+(MyNumber lNum, MyNumber rNum)
{
	return lNum.schoolAdd(rNum);
}

MyNumber operator-(MyNumber lNum, MyNumber rNum)
{
	return lNum.schoolSub(rNum);
}

MyNumber operator*(MyNumber lNum, MyNumber rNum)
{
	return lNum.karatsubaMult(rNum);
}

MyNumber operator/(MyNumber lNum, MyNumber rNum)
{
	return lNum.divide(rNum);
}

MyNumber::MyNumber(int digits, int base)
{
	this->base = base;
	int length = 1;
	int tbase = base;
	bool tensPow = true;
	while (tbase >= 10)
	{
		if (tensPow && (tbase % 10 != 0))
		{
			tensPow = false;
		}
		tbase /= 10;
		++length;
	}
	if (tensPow && tbase == 1) {
		length -= 1;
	}
	this->digitWid = length;
	int zeros = 1;
	for (int i(0); i < length; i++)
	{
		zeros *= 10;
	}
	while (digits > 0)
	{
		this->digits.push_back(digits%zeros);
		digits /= zeros;
	}
}

MyNumber::MyNumber(string digits, int base)
{
	this->base = base;
	this->digitWid = 1;
	int length = digits.length();
	for (int i(0); i < length; ++i)
	{
		//string digit(1, digits.at(length - 1 - i));
		this->digits.push_back(digits.at(length - 1 - i)-48);
	}
	this->normalize();
}

MyNumber::MyNumber(vector<int> digits, int base)
{
	this->base = base;
	this->digits = digits;
	int length = 1;
	int tbase = base;
	bool tensPow = true;

	while (tbase >= 10)
	{
		if (tensPow && (tbase % 10 != 0))
		{
			tensPow = false;
		}
		tbase /= 10;
		++length;
	}
	if (tensPow && tbase == 1) {
		length -= 1;
	}
	this->digitWid = length;
}

MyNumber::~MyNumber()
{
}

vector<int> MyNumber::getDigits()
{
	return this->digits;
}

int MyNumber::getBase()
{
	return this->base;
}

int MyNumber::getDigitWid()
{
	return this->digitWid;
}

void MyNumber::normalize()
{
	int size = this->digits.size();
	for (int i(size - 1); i > 0; --i)
	{
		if (digits.at(i) == 0)
		{
			digits.pop_back();
		}
		else
		{
			break;
		}
	}
}

MyNumber MyNumber::operator-()
{
	int base = this->base;
	vector<int> digits = this->digits;
	for (int i(0); i < digits.size(); ++i)
	{
		digits.at(i) = -digits.at(i);
	}
	return MyNumber(digits, base);
}

MyNumber MyNumber::schoolAdd(MyNumber rhs)
{
	vector<int> lDigits = this->digits;
	vector<int> rDigits = rhs.getDigits();
	vector<int> result;
	int length = lDigits.size() > rDigits.size() ? lDigits.size() : rDigits.size();
	int base = this->base;
	int ai = 0;
	int bi = 0;
	int ci = 0;
	int current = 0;
	for (int i(0); i < length; ++i)
	{
		ai = 0;
		bi = 0;
		if (i < lDigits.size())
		{
			ai = lDigits.at(i);
		}
		if (i < rDigits.size())
		{
			bi = rDigits.at(i);
		}
		current = ai + bi + ci;

		if (current >= base)
		{
			current -= base;
			ci = 1;
		}
		else
		{
			ci = 0;
		}
		result.push_back(current);
	}
	if (ci != 0)
	{
		result.push_back(ci);
	}
	return MyNumber(result, base);
}

MyNumber MyNumber::schoolSub(MyNumber rhs)
{
	vector<int> lDigits = this->digits;
	vector<int> rDigits = rhs.getDigits();
	vector<int> result;
	int length = lDigits.size();
	int base = this->base;
	int ai = 0;
	int bi = 0;
	int ci = 0;
	int current = 0;
	for (int i(0); i < length; ++i)
	{
		ai = 0;
		bi = 0;
		if (i < lDigits.size())
		{
			ai = lDigits.at(i);
		}
		if (i < rDigits.size())
		{
			bi = rDigits.at(i);
		}
		current = ai - bi + ci;

		if (current < 0)
		{
			current += base;
			ci = -1;
		}
		else
		{
			ci = 0;
		}
		result.push_back(current);
	}
	if (ci != 0)
	{
		result.push_back(ci);
	}
	return MyNumber(result, base);
}

MyNumber recursionMult(MyNumber lhs, MyNumber rhs)
{
	int base = lhs.getBase();
	vector<int> lDigits = lhs.getDigits();
	vector<int> rDigits = rhs.getDigits();
	vector<int> result;

	int length = lDigits.size() > rDigits.size() ? lDigits.size() : rDigits.size();
	while (lDigits.size() < length)
	{
		lDigits.push_back(0);
	}
	while (rDigits.size() < length)
	{
		rDigits.push_back(0);
	}
	if (lDigits.size() == 1 && rDigits.size() == 1)
	{
		int product = lDigits.at(0) * rDigits.at(0);
		int carry = product / base;
		int value = product % base;
		result.push_back(value);
		result.push_back(carry);
		return MyNumber(result, base);
	}
	else
	{
		int half = length / 2;
		vector<int> a1Digits;
		vector<int> a0Digits;
		vector<int> b1Digits;
		vector<int> b0Digits;

		for (int i(0); i < length; ++i)
		{
			if (i < half)
			{
				a0Digits.push_back(lDigits.at(i));
				b0Digits.push_back(rDigits.at(i));
			}
			else
			{
				a1Digits.push_back(lDigits.at(i));
				b1Digits.push_back(rDigits.at(i));
			}
		}

		int Bk = half;
		MyNumber a1(a1Digits, base);
		MyNumber a0(a0Digits, base);
		MyNumber b1(b1Digits, base);
		MyNumber b0(b0Digits, base);

		MyNumber r2 = recursionMult(a1, b1);
		MyNumber r1 = recursionMult(a0 + a1, b0 + b1);
		MyNumber r0 = recursionMult(a0, b0);

		//MyNumber r9 = recursionMult(a1, b0);
		//MyNumber r8 = recursionMult(a0, b1);
		//MyNumber r7 = r9 + r8;

		MyNumber r3 = r1 - r2 - r0;
		r2.shift(Bk * 2);
		r3.shift(Bk);
		MyNumber result = r0 + r2 + r3;

		//r7.shift(Bk);
		//MyNumber result2 = r2 + r7 + r0;

		return result;
	}
}

MyNumber MyNumber::karatsubaMult(MyNumber rhs)
{
	MyNumber lhs(this->digits, this->base);
	MyNumber product = recursionMult(lhs, rhs);
	product.normalize();
	return product;
}

MyNumber MyNumber::divide(MyNumber rhs)
{
	MyNumber quotient("0", this->base);
	MyNumber one("1", this->base);
	if (rhs > *this)
	{
		return quotient;
	}
	else if (*this == rhs)
	{
		return one;
	}
	else
	{
		MyNumber rem(*this);
		vector<int> lDigits = rem.getDigits();
		vector<int> rDigits = rhs.getDigits();
		int lLength = lDigits.size();
		int rLength = rDigits.size();

		while (rem > rhs)
		{
			MyNumber timesOfRhs("1", this->base);
			MyNumber shiftedRhs = rhs;
			if (rem.getDigits().at(lLength - 1) > rDigits.at(rLength - 1)) {
				timesOfRhs.shift(lLength - rLength);
				shiftedRhs.shift(lLength - rLength);
			}
			else
			{
				timesOfRhs.shift(lLength - rLength - 1);
				shiftedRhs.shift(lLength - rLength - 1);
			}
			quotient = quotient + timesOfRhs;
			rem = rem - shiftedRhs;

			rem.normalize();
			lLength = rem.getDigits().size();
		}
		if (rem == rhs)
		{
			return quotient + one;
		}
		else
		{
			return quotient;
		}
	}
}

void MyNumber::shift(int position)
{
	for (int i(0); i < position; ++i)
	{
		this->digits.insert(digits.begin(), 0);
	}
}


void MyNumber::print()
{
	for (int i(digits.size() - 1); i >= 0; i--)
	{
		cout << digits.at(i);
	}
}

// string numGenerator(int base) {

// 	int digits = rand() % 20 + 1;
// 	string num;
// 	for (int i(0); i < digits; i++)
// 	{
// 		int randNum = rand() % base;
// 		num.append(to_string(randNum));
// 	}
// 	return num;
// }

void run(string a, string b, int base) {

	MyNumber numA(a, base);
	MyNumber numB(b, base);

	MyNumber numC = numA + numB;
	MyNumber numD = numA * numB;
	MyNumber numE = numA / numB;

	// cout << "a = ";
	// numA.print();
	// cout << " b = ";
	// numB.print();
	// cout << " ";
	numC.print();
	cout << " ";
	numD.print();
	cout << " ";
	numE.print();
	cout << endl;

}

int main() {

	int base;

	string a;
	string b;

	cin>>a;
	cin>>b;
	cin>>base;
	run(a,b,base);

	// for (int i(0); i < 3; ++i)
	// {
	// 	string a = numGenerator(base);
	// 	string b = numGenerator(base);
	// 	run(a, b, base);
	// }

	// base = 2;

	// a = "101011";
	// b = "101010101";

	// for (int i(0); i < 3; ++i)
	// {
	// 	string a = numGenerator(base);
	// 	string b = numGenerator(base);
	// 	run(a, b, base);
	// }

	// base = 8;

	// a = "452747621621";
	// b = "6421565147";

	// for (int i(0); i < 3; ++i)
	// {
	// 	string a = numGenerator(base);
	// 	string b = numGenerator(base);
	// 	run(a, b, base);
	// }

	// run("100", "100", 10);

	return 0;
}
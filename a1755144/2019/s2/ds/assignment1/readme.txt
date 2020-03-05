1. run.sh
This will prepare the environment for clients, including compiling java files, starting the Registry, starting the server
	(Or do it manually with the following command
	1.javac *.java
	2.rmiregistry &
	3.java CalculatorServer &)
2. java MultiExec
This will run the test cases being settled, and show the testing result



Try the clients yourself:

Simple calculator: Clients works on the same stack
java CalculatorClient [input file name] [output file name]
Bonus calculator: Clients have their own stack
java CalculatorClientBonus [input file name] [output file name]

The default file names are input and output

You can edit a text file to instruct the client what to do
Elements:
    1.Numbers: Push the number onto the stack
    2.+-/+: Perform the specified operation
    3.p: Pop the number on the top of the stack and print it to the output file
    4.p + Number: same as 3., but with a delay    ex. dp100 delay for 100 millisecond and then pop
Each element should separate by a space or a new line
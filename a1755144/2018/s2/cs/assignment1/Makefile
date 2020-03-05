# must specify make target
all: tokeniser

# C++ 11
CXXFLAGS=--std=c++11 -I. -Iincludes

MYH:=${wildcard my*.h}
MYCPP:=${wildcard my*.cpp}
MYO:=${MYCPP:.cpp=.o}
tokeniser.o ${MYO}: ${MYH}

tokeniser: tokeniser.o ${MYO} lib/tokens.o
	g++ ${CXXFLAGS} -o $@ $^

clean:
	-@rm -f tokeniser *.o

# regenerate all expected test outputs using working tokeniser
test-new:
	-@bash lib/run-tests.bash new loud

# run tests using the working tokeniser - they should all pass!
test-working:
	-@bash lib/run-tests.bash working loud

# generate any missing expected test outputs using working tokeniser
test-add:
	-@bash lib/run-tests.bash add loud

# regenerate all expected test outputs using working tokeniser - quietly
test-new-quiet:
	-@bash lib/run-tests.bash new quiet

# generate any missing expected test outputs using working tokeniser - quietly
test-add-quiet:
	-@bash lib/run-tests.bash add quiet

# run tests using the working tokeniser - they should all pass! - quietly
test-working-quiet:
	-@bash lib/run-tests.bash working quiet

# run tests using the student tokeniser
test-tokeniser: test
test: tokeniser
	-@bash lib/run-tests.bash tokeniser loud

# run tests using the student tokeniser - quietly
test-tokeniser-quiet: test-quiet
test-quiet: tokeniser
	-@bash lib/run-tests.bash tokeniser quiet

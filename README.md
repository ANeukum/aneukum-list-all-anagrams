# Build and run

This project was created using jdk 1.8 and maven 3.6.0.
If you have them on path you can run the program using the following commands:

    git clone https://github.com/ANeukum/aneukum-list-all-anagrams.git

    cd aneukum-list-all-anagrams

    mvn test

    cd target/classes

    java aneukum.bewerbung.lidl.Main
    
This should result in the required output.
If problems occur the project can be built and run like like any vanilla java project.

# Design
While scanning the input file the chosen implementation puts each of its lines in a key-value map.
The keys are chosen such that two lines have the same key if an only if they are anagrams.
The values are lists containing all strings that are anagrams to each other. 
The result can then be obtained by printing all map values that have more than one element.
A reasonable design consist therefore of a class for the keys and a class containing the insert and print logic for the map.

The keys are obtained by counting the characters in each line. 
For example, the key of "ABCA" would contain the following information: 2xA, 1xB, 1xC.
Alternatively the key could be the sorted string.
While counting takes linear time and is therefore asymptotically faster than sorting the lines seem to be rather short. 
Therefore other effects will dominate the execution time.

As Java Strings are UTF-16 encoded it is necessary to choose methods that are aware of surrogate pairs.
Otherwise it is possible to construct false positives as can be seen in one of the test cases.

## Maintainability
tests
immutablility
equals contract

## Scalability
stream save RAM

## Performance
probably too many maps, low lovel arrays?
hash code collisions

## External Libraries
The only external library used is JUnit5.

# Scaling

## 10 million words: 
It is reasonable to expect that the RAM of a modern computer is sufficient for input files with 10 million words.
A short test revealed that the current implementation takes a couple of minutes to process such a file.
The processor was the bottleneck therefore paralellization could be beneficial.

But first, the input should be sorted according to word length since words of different length can not be anagrams.
Such a sorting can be achieved in linear time using bucket sort.
This also allows for preallocation of arrays.

The task at hand does not lend itself to full parallelization.
Whether a String is an anagram can only be decided if all other Strings are processed.
Therefore, if the input file is split in multiple parts that are processed inividually as if they were the entire input some a posteriori merging becomes necessary.
Alternatively, the key-value map could be synchronized resulting in a certain amount of contention limiting the reasonable degree of parallelization.
The amount of contention could be determined by measuring the fraction of time a single thread spends accessing the map.

## 100 billion words:
At an input size of 100 billion words the RAM becomes a limiting factor.
Once again there is the possibility of splitting the input into parts, merging the individual outputs later on.
If these individual outputs are sorted they can be merged line by line from top to bottom.

Instead of using files the words could be inserted into a database.
This approach would probably result in an extraordinary amount of database expertise.

Depending on the use case a potential solution is to remove duplicates.
For natural languages this results in a manageable input size.
For other use cases, for exmple cryptography, this is not that beneficial.

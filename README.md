# Build and run

This project was created using jdk 1.8 and maven 3.6.0.
If you have them on path you can run the program using the following commands:

    git clone https://github.com/ANeukum/aneukum-list-all-anagrams.git

    cd aneukum-list-all-anagrams

    mvn test

    cd target/classes

    java aneukum.bewerbung.lidl.Main
    
# Design

Maintainability
Scalability
Performance
External libreries: Junit 5

# Scaling

10 million words: 
    optimize
    parallelize (contention: synchronize or merge later)
    
100 billion words:
    runs out of memory
    similar to external sorting

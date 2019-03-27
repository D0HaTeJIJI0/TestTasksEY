# TestTasksEY
1. Task 1

## Command in prompt to generate files:  

    java -jar Task1\target\Task1-1.0-SNAPSHOT-jar-with-dependencies.jar -g "target_directory"

## Command in prompt to generate files using exe file:  

    Task1\Task1.exe -g "target_directory"
    
## Example of working:
![alt text](https://github.com/D0HaTeJIJI0/TestTasksEY/tree/master/images/Task1/GenerateFiles.png)

## Command in prompt to combine files:  

    java -jar Task1\target\Task1-1.0-SNAPSHOT-jar-with-dependencies.jar -c "source_directory" "target_file" ["string_to_exclude"]

## Command in prompt to combine files using exe file:  

    Task1\Task1.exe -c "source_directory" "target_file" ["string_to_exclude"]
    
## Examples of working:
![alt text](https://github.com/D0HaTeJIJI0/TestTasksEY/tree/master/images/Task1/UniteFiles.png)
![alt text](https://github.com/D0HaTeJIJI0/TestTasksEY/tree/master/images/Task1/UniteFilesWithExclusion.png)

## Programm arguments to import files(doesn't work with jar):  

    -i "source_file"|"source_directory"

## Examples in IntelliJ:
![alt text](https://github.com/D0HaTeJIJI0/TestTasksEY/tree/master/images/Task1/ImportFile.png)
![alt text](https://github.com/D0HaTeJIJI0/TestTasksEY/tree/master/images/Task1/ImportDirectory.png)

## To execute sql you need to create StoredProcedure with code in Task1/StoredProcedure.sql

## Examples pgAdmin:
![alt text](https://github.com/D0HaTeJIJI0/TestTasksEY/tree/master/images/Task1/StoredProcedure.png)

2. Task 2
 ## Example of working web-app:
 ![alt text](https://github.com/D0HaTeJIJI0/TestTasksEY/tree/master/images/Task2/FileTable.png)

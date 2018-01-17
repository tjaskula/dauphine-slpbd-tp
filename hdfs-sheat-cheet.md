## Hadoop Distributed File System cheat sheet
- **cat**: Copies source paths to stdout.
    Usage: hdfs dfs -cat URI [URI …]

    Example:
    ```
    hdfs dfs -cat hdfs://<path>/file1

    hdfs dfs -cat file:///file2 /user/hadoop/file3
    ```

- **chgrp**: Changes the group association of files. With -R, makes the change recursively by way of the directory structure. The user must be the file owner or the superuser.
    Usage: hdfs dfs -chgrp [-R] GROUP URI [URI …]

- **chmod**: Changes the permissions of files. With -R, makes the change recursively by way of the directory structure. The user must be the file owner or the superuser
    Usage: hdfs dfs -chmod [-R] <MODE[,MODE]… | OCTALMODE> URI [URI …]

    Example:
    ```
    hdfs dfs -chmod 777 test/data1.txt
    ```

- **chown**: Changes the owner of files. With -R, makes the change recursively by way of the directory structure. The user must be the superuser.
    Usage: hdfs dfs -chown [-R] [OWNER][:[GROUP]] URI [URI ]

    Example:
    hdfs dfs -chown -R hduser2 /opt/hadoop/logs

- **count**: Counts the number of directories, files, and bytes under the paths that match the specified file pattern.
    Usage: hdfs dfs -count [-q] <paths>

    Example:
    ```
    hdfs dfs -count hdfs://nn1.example.com/file1 hdfs://nn2.example.com/file2
    ```

- **cp**: Copies one or more files from a specified source to a specified destination. If you specify multiple sources, the specified destination must be a directory.
    Usage: hdfs dfs -cp URI [URI …] <dest>

    Example:
    ```
    hdfs dfs -cp /user/hadoop/file1 /user/hadoop/file2 /user/hadoop/dir
    ```

- **du**: Displays the size of the specified file, or the sizes of files and directories that are contained in the specified directory. If you specify the -s option, displays an aggregate summary of file sizes rather than individual file sizes. If you specify the -h option, formats the file sizes in a “human-readable” way.
    Usage: hdfs dfs -du [-s] [-h] URI [URI …]

    Example:
    ```
    hdfs dfs -du /user/hadoop/dir1 /user/hadoop/file1
    ```

- **dus**: Displays a summary of file sizes; equivalent to hdfs dfs -du –s.
    Usage: hdfs dfs -dus <args>

- **expunge**: Empties the trash. When you delete a file, it isn’t removed immediately from HDFS, but is renamed to a file in the /trash directory. As long as the file remains there, you can undelete it if you change your mind, though only the latest copy of the deleted file can be restored.

    Usage: hdfs dfs –expunge

- **get**: Copies files to the local file system. Files that fail a cyclic redundancy check (CRC) can still be copied if you specify the –ignorecrc option. The CRC is a common technique for detecting data transmission errors. CRC checksum files have the .crc extension and are used to verify the data integrity of another file. These files are copied if you specify the -crc option.
    Usage: hdfs dfs -get [-ignorecrc] [-crc] <src> <localdst>

    Example:
    ```
    hdfs dfs -get /user/hadoop/file3 localfile
    ```

- **getmerge**: Concatenates the files in src and writes the result to the specified local destination file. To add a newline character at the end of each file, specify the addnl option.
    Usage: hdfs dfs -getmerge <src> <localdst> [addnl]

    Example:
    ```
    hdfs dfs -getmerge /user/hadoop/mydir/ ~/result_file addnl
    ```

- **ls**: Returns statistics for the specified files or directories.
    Usage: hdfs dfs -ls <args>

    Example:
    ```
    hdfs dfs -ls /user/hadoop/file1
    ```

- **lsr**: Serves as the recursive version of ls; similar to the Unix command ls -R.
    Usage: hdfs dfs -lsr <args>

    Example:
    ```
    hdfs dfs -lsr /user/hadoop
    ```

- **mkdir**: Creates directories on one or more specified paths. Its behavior is similar to the Unix mkdir -p command, which creates all directories that lead up to the specified directory if they don’t exist already.
    Usage: hdfs dfs -mkdir <paths>

    Example:
    ```
    hdfs dfs -mkdir /user/hadoop/dir5/temp
    ```

- **mv**: Moves one or more files from a specified source to a specified destination. If you specify multiple sources, the specified destination must be a directory. Moving files across file systems isn’t permitted.
    Usage: hdfs dfs -mv URI [URI …] <dest>

    Example:
    ```
    hdfs dfs -mv /user/hadoop/file1 /user/hadoop/file2
    ```

- **put**: Copies files from the local file system to the destination file system. This command can also read input from stdin and write to the destination file system.
    Usage: hdfs dfs -put <localsrc> … <dest>

    Example:
    ```
    hdfs dfs -put localfile1 localfile2 /user/hadoop/hadoopdir; hdfs dfs -put – /user/hadoop/hadoopdir (reads input from stdin)
    ```

- **rm**: Deletes one or more specified files. This command doesn’t delete empty directories or files. To bypass the trash (if it’s enabled) and delete the specified files immediately, specify the -skipTrash option.
    Usage: hdfs dfs -rm [-skipTrash] URI [URI …]

    Example:
    ```
    hdfs dfs -rm hdfs://nn.example.com/file9
    ```

- **rmr**: Serves as the recursive version of –rm.
    Usage: hdfs dfs -rmr [-skipTrash] URI [URI …]

    Example:
    ```
    hdfs dfs -rmr /user/hadoop/dir
    ```


- **stat**: Displays information about the specified path.
    Usage: hdfs dfs -stat URI [URI …]

    Example:
    ```
    hdfs dfs -stat /user/hadoop/dir1
    ```

- **tail**: Displays the last kilobyte of a specified file to stdout. The syntax supports the Unix -f option, which enables the specified file to be monitored. As new lines are added to the file by another process, tail updates the display.
    Usage: hdfs dfs -tail [-f] URI

    Example:
    ```
    hdfs dfs -tail /user/hadoop/dir1
    ```

- **touchz**: Creates a new, empty file of size 0 in the specified path.
    Usage: hdfs dfs -touchz <path>

    Example:
    ```
    hdfs dfs -touchz /user/hadoop/file12
    ```

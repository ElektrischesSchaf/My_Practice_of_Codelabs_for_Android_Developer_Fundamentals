# AAD_1072_HW10

Please follow the instructions on the **Homework** sections in these codelabs.

- [10.1 Part A: Room, LiveData, and ViewModel](https://codelabs.developers.google.com/codelabs/android-training-livedata-viewmodel/#18)
- [10.1 Part B: Deleting data from a Room database](https://codelabs.developers.google.com/codelabs/android-training-room-delete-data/index.html?index=..%2F..%2Fandroid-training#12)


## Part 1. Questions (40 pt)
Please submit your answer on moodle.
<https://moodle.ncku.edu.tw/course/view.php?id=104771>

**[Notice]** 
- You only have **one chance** to submit your answer.
- There are two Questionnaire in this Homework (20 pt for each)

| Codelab | Questions | Questionnaire Name |
| --- | ----------- | ---------|
| 10.1 Part A | 4 Questions | Homework 10 |
| 10.1 Part B | 2 Questions | Homework 10 |
| 10.1 Part B | 6 Matching Question | Homework 10.1 Part B - codelab |

## Part 2. Android Tests (60 pt)

Please submit your code to the **master** branch in this repository for grading.

<table>
    <thead>
        <tr>
            <th>Codelab</th>
            <th>Starter Project</th>
            <th>Test File</th>
            <th>Questions</th>
            <th>Points</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td rowspan=4>10.1</td>
            <td rowspan=4>RoomWordsWithDelete</td>
            <td rowspan=4>RoomDatabaseTest</td>
            <td>testAnswerLayout</td>
            <td>15 pt</td>
        </tr>
        <tr>
            <td>testInsertData</td>
            <td>15 pt</td>
        </tr>
        <tr>
            <td>testUpdateAnswer</td>
            <td>15 pt</td>
        </tr>
        <tr>
            <td>testRestoreData</td>
            <td>15 pt</td>
        </tr>
    </tbody>
</table>

### solution sample
|test name |solution |
|----|----|
|testAnswerLayout| ![solution_layout](sample_pictures/solution_layout.png) |
|testInsertData | ![insert_sample](./sample_pictures/insert_sample.gif) |
|testUpdateAnswer | ![update_sample](./sample_pictures/update_sample.gif)|
|testRestoreData | ![restore_sample](./sample_pictures/restore_sample.gif)|

**[Notice]** 
- Please do not modify the following files:
    - .travis.yml
    - <Project>/app/src/androidTest/*
    - gradle files
- Once any modifications or any cheating behavior are detected, you will got 0 pt for this homework.
- Creating a new branch to develop and testing locally are highly recommended.

# SCD2_2021_Exam
## Style Guide
Code style: Google Java Style Guide https://google.github.io/styleguide/javaguide.html

Code style tooling: https://github.com/google/google-java-format

The style guide is only used for coding _style_ and documentation is applied to methods and
classes at the discretion of the developer.

## Configuration Instructions
* Java (8 or above)
* Javafx 13
* gradle 6.8.3

## Installation & Execution Instructions
1. Clone this repository: https://github.sydney.edu.au/qyan6939/SCD2_2021_Exam.git
2. Open IntelliJ IDEA. 
3. Open the project and choose the cloned repository.
4. Run `gradle build`
5. Run `gradle run --args="mode mode"` in the terminal.
    * Note that there are only 2 valid mode options, `online` and `offline`. The first mode option is for controlling the input API, and the second controlling the output API.
    * So there are 4 legal commands can be run successfully:
        * `gradle run --args="online online"`
        * `gradle run --args="online offline"`
        * `gradle run --args="offline online"`
        * `gradle run --args="offline offline"`

## TDD Process Record
* Login Function
    * [TDD RED PHASE] finish model domain testing      
    https://github.sydney.edu.au/qyan6939/SCD2_2021_Exam/commit/8ae7676fdae410a194c51baee92c00fbf882b947
    * [TDD GREEN PHASE] finish model domain implementation and passed testings
    https://github.sydney.edu.au/qyan6939/SCD2_2021_Exam/commit/867bbbb02b746dc00b26ae1e873addd629dc0985
    * [TDD REFACTOR PHASE]        
    https://github.sydney.edu.au/qyan6939/SCD2_2021_Exam/commit/495233cf7ad738c2dd73c80a9c100b20c587c841
    * [TDD RED PHASE] finish UserService testing        
    https://github.sydney.edu.au/qyan6939/SCD2_2021_Exam/commit/495233cf7ad738c2dd73c80a9c100b20c587c841

* Search By Tag Function


## Reference
* Software Background Diagram   
https://www.wallpaperflare.com/library-cartoon-books-candles-ladder-ladders-biblioteca-wallpaper-rbsw

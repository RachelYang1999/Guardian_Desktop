# SCD2_2021_Exam

## Introduction
![image](https://github.sydney.edu.au/qyan6939/Image/blob/master/Welcome_Page.jpg)  
This is a desktop application for searching articles of Guardian. You can search relative tags by a keyword first and get articles by selecting a specific tag. After getting the article information, users can get the URL of Pastebin for sharing and checking the information via this URL online.
* Input API  
  https://open-platform.theguardian.com/
* Output API  
  https://pastebin.com/doc_api
  
## Implemented Feature Level
**DISTINCTION** (SQLite database + Concurrency)  

**Self-Extension**
* You can choose to get all tag results **or** just 1 default API response page size result by entering a keyword.  
  ![image](https://github.sydney.edu.au/qyan6939/Image/blob/master/Extension_Function1%20(2).jpg)
* You can choose to get all article results **or** just 1 default API response page size result by selecting a tag.  
  ![image](https://github.sydney.edu.au/qyan6939/Image/blob/master/Extension_Function2.jpg)
  
## Style Guide
Code style: Google Java Style Guide https://google.github.io/styleguide/javaguide.html

Code style tooling: https://github.com/google/google-java-format

## Configuration Instructions
* Java (8 or above)
* Javafx 13
* gradle 6.8.3
* SQLite 3.34.0

## Installation & Execution Instructions
1. Clone this repository: https://github.sydney.edu.au/qyan6939/SCD2_2021_Exam.git
2. Open IntelliJ IDEA. 
3. Select "Open" button and choose the cloned repository folder.
4. Run `gradle build` in the terminal
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
    * [TDD GREEN PHASE] finish UserService implementation and passed testings
    https://github.sydney.edu.au/qyan6939/SCD2_2021_Exam/commit/efb3db185be74d1fc940b15f8a773cb44d06be43
    
* Search Function
    * [TDD RED PHASE - Search By Tag Function] finish ArticleService testing        
    https://github.sydney.edu.au/qyan6939/SCD2_2021_Exam/commit/8aa7c7d5213137d28c81e7fb731b7c1c2627045e
    * [TDD GREEN PHASE - Search By Tag Function] finish ArticleSerive implementation and passed testings
    https://github.sydney.edu.au/qyan6939/SCD2_2021_Exam/commit/c53784d0ae7924a3614d996bc3fa74b6a3665307
    * [TDD REFACTOR PHASE] refactor Article
    https://github.sydney.edu.au/qyan6939/SCD2_2021_Exam/commit/d9c4931b1d2d2918e62cbc457fc61d1724f2c688
    * [TDD RED PHASE - Get Pastebin Link Function] finish PastebinService testing
    https://github.sydney.edu.au/qyan6939/SCD2_2021_Exam/commit/24b17c0b377b3a554eccb5a4f45ddce47fc7e27c
    * [TDD RED PHASE - Get Pastebin Link Function & ArticleService (Because of refactoring)] finish PastebinService&ArticleService testing
    https://github.sydney.edu.au/qyan6939/SCD2_2021_Exam/commit/1111999777e5e990b48c634ea065359e81b9ac4e
    * [TDD GREEN PHASE & REFACTORED] finish ArticleService PastebinService implementation, passed testings and did some refactor works
    https://github.sydney.edu.au/qyan6939/SCD2_2021_Exam/commit/9e9a7cc3b697b07dcf2d623fcabf2c4660dc9d54
    * [REFACTOR] Add Article entity attribute for better storing data
    https://github.sydney.edu.au/qyan6939/SCD2_2021_Exam/commit/0f6844cb9501aa372e9a421555b358ca98073aba
    * [TDD RED PHASE - Search Tags Function (MISSED BEFORE)] finish TagService testing & Some GUI modification
    https://github.sydney.edu.au/qyan6939/SCD2_2021_Exam/commit/70e8d9b1e60ff922cfd76a5934edd8a26779121d
    * [TDD GREEN PHASE] finish TagService implementation and passed testings
    https://github.sydney.edu.au/qyan6939/SCD2_2021_Exam/commit/8ca369d5a713f1c792412f748c62f25a0e8a4b9e

* Exam Extended Function   
    * [TDD RED PHASE - User Input Integer Function] finish model.domain User and model.service UserService testing   
    https://github.sydney.edu.au/qyan6939/SCD2_2021_Exam/commit/1013b4680578549d1748f31171df4bf33d2b94ea
    * [TDD GREEN PHASE - User Input Integer Function] finish model.domain User and model.service UserService implementation and passed testings.  
    https://github.sydney.edu.au/qyan6939/SCD2_2021_Exam/commit/e7d170184619ac5587f1ac63ce063af32bcb3846
    * [TDD REFACTOR] Avoid non-logged in user to indicate the integer
    https://github.sydney.edu.au/qyan6939/SCD2_2021_Exam/commit/44888687474321540773db1b566699490605b147

## Simple Extension Explanation
Approaching the due time, I found there is a font issue with Javafx depends on different operating system settings, to make the front-end display effect to be system-independent, I made some modifications about font setting in my code. 

## Font Issue Possibility For MacOS
There might be a font-related CoreText warning in some version of MacOS.
* Normal Alert Box   
  ![image](https://github.sydney.edu.au/qyan6939/Image/blob/master/Normal_Error_Box.png)
* Error Alert Box with font issue  
  ![image](https://github.sydney.edu.au/qyan6939/Image/blob/master/Error%20Box%20Font%20Issue.png)
  
Please set a breakpoint on CTFontLogSystemFontNameRequest to solve this issue if you met it.
https://stackoverflow.com/questions/58706570/how-can-i-find-the-source-of-this-font-related-coretext-warning-in-ios13

## Reference
* Software Background Diagram   
https://www.wallpaperflare.com/library-cartoon-books-candles-ladder-ladders-biblioteca-wallpaper-rbsw

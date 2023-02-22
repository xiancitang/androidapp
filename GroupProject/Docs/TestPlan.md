# Test Plan

**Author**: \<Xianci Tang\>

## 1 Testing Strategy

### 1.1 Overall strategy

This Job off comparison app is a single-user single-system app, we will write Junit tests to cover backend  and  perform manual testing to cover UI and System integration.  

Testing Process: 
- Each developer will test the parts they developed 
- Developer will cross test another developer's development part
- Xianci and Jun Dong will perform System testing

### 1.2 Test Selection
When we construct test cases,Requirement doc and Use-case model are the base reference .
 we use both black-box and white-box techniques.
* UI and System testing (black box)
* Backend functions (white-box)
* Also include happy path and negative path


### 1.3 Adequacy Criterion

* Check against Use-case model doc,  make sure all use cases are covered in tests.
* Check against requirement doc, make sure all functions , use user path and requirements are covered



### 1.4 Bug Tracking

We use a Project Status File to track bug and enhancement:
* Table with bug/enhancement description, fix status and bug_report time and fix_bug time fields ,one per row .
* Each time when a bug is found ,enter a new row with description, assign to developers

### 1.5 Technology

Junit Test Framework


## 2 Test Cases

* App Component: whether UI or backend side*



| App Component | Purpose                                              | Steps To Perform                                                | Expected Result                                                                                                                                                                                      |  Actual Result |  Test Status | Note |
|:--------------|------------------------------------------------------|-----------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------|---------|---|
| ui            | main menu with 3 options ( no job offer entered yet) | start app                                                       | see main menu with  3 options listed : enter/edit current job, enter job offer, adjust comparsion settings                                                                                           |  main menu with 4 buttons options listed, compare job offer button is grayed out; comparison settings shown       |   Pass      | new design with 4 buttons originally; just gray out the compare button  | 
| ui            | main menu with 4 options                             | start app                                                       | see main menu with all 4 options listed : enter/edit current job, enter job offer, adjust comparsion settings, compare job offer                                                                     |  main menu with 4 buttons options listed and clickable, comparison settings shown         |   Pass      |   |
| ui            | enter or edit current job                            | start app                                                       | show enter current job option in main menu if no current job exists, otherwise show edit current job option in the main menu                                                                         |   current job interface shown correctly; button shows differently according to current job's existence        |    Pass     |   |
| ui            | enter current job                                    | start app, click enter current job option(no current job)       | get redirected to an new interface with 9 input fields show up , have save, cancel button at bottom,able to save or cancel and return to main menu after click save or cancel                        |      correct interface shown; 9 input fields shown; correct buttons shown.     |   Pass      |   |
| ui            | edit current job                                     | start app, click edit current job option                        | get redirected to an new interface with 9 input fields having existing value, able to edit input value, able to save or cancel then return to main menu after save or cancel                         |    correct interface shown; 9 input fields with existing values shown; correct buttons shown.       |    Pass     |   |
| ui            | enter job offer                                      | start app, click enter job offer option                         | get redirected to an new interface with 9 input fields show up ,have save,cancel options,able to enter value then save or cancel                                                                     |  correct interface shown; 9 input fields shown; correct buttons shown.         |    Pass     |   |
| ui            | enter job offer then save                            | start app, click enter job offer option, enter detail and save  | get redirected to an new interface with enter new offer, compare offer(if current job is present) and return to main menu options listed,able to enter new offer,compare offer or return to main menu |    correct interfaces entered; offer successfully saved, able to enter new offer detail, saved in database. Compare button no response       |   Fail |  add/fix in next iteration (next week) | 
| ui            | enter job offer then cancel                          | start app, click enter job offer option, enter detail and cancel | get redirected to an new interface with enter new offer, return to main menu options listed,able to enter new offer, or return to main menu                                                          |     correct interface redirected and buttons are shown correctly, not saved in database      |  Pass       |   |
| ui            | adjust comparison setting                            | start app,  click adjust comparison setting option              | get redirected to an new interface with 6 weight fields having default value 1, able to change their value                                                                                           |   Interface shown correctly; seekbars are shown correctly and could be toggled freely.       |   Pass      |  |
| ui            | compare job offer                                    | start app, click compare job offer option                       | get redirected to an new interface with all jobs listed with Title and Company fields and ranked from best to worst, current job indicated if present                                                |  correct interface shown; job information listed and ranked correctly. Current job is marked with *         |  Pass      |   |
| ui            | compare two job offer                                | start app, click compare job offer option, then select two jobs | see a table displaying two jobs details with 9 fields, having performa another comparasion option and return to main menu option at bottom                                                           | correct interfaces shown with jobs' properties; buttons shown correctly          |   Pass      |   |
| backend       | job score calculation                                | junit test with different weight set                            | corresponding value calculated with AYS + AYB + (LT * AYS / 260) + (CSO/2) + HBP + WF                                                                                                                |    tested 3 number groups and correct score being calculated       |     Pass    |  more specific weights group cases to be designed (with JUnit) |
| backend       | job ranking and sorting                              | manul check job are listed with scores are from high to low     | jobs are displayed with score from high to low                                                                                                                                                       |  Jobs displaye correctly by scores, from high to low         |  Pass       |   |
| data          | data persistance                                     | start app ,fill in job offer and current job,close app and restart app | still can see previous entered job offer or current job                                                                                                                                              |      saved current job & job offers still exist     |  Pass       |   |


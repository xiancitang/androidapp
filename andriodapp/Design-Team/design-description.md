1. When the app is started, the user is presented with the main menu, which allows the
user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison
settings, or (4) compare job offers (disabled if no job offers were entered yet).

Solution: 

A "Menu" class is created to be the entry point for the app system. When the app is started, the GUI will
present the user with four buttons corresponds to the four required operations.

(1)To enter or edit current job details/enter job offers- 
There would be a attribute "CurrentJob" of boolean type to indicate the status of current job. If "CurrentJob" = false, it means the current job is not entered. When operation (1) is clicked, the app will display the page to enter the current job. Otherwise, the app will display the page of current job information and allow user to modify the current job. By default, "CurrentJob" = false.

(2) enter job offers and (3) adjust the comparison settings, GUI will display the corresponding interfaces. See solutions to 3 and 4 for implementation.

(4) compare job offers (disabled if no job offers were entered yet).
An attribute "NoOfJobOffers" is created in "Menu" to track number of job offers entered. By default, "NoOfJobOffers" = 0. Whenever a job offer is saved, "NOOfJobOffers" increase by 1. If "NOOfJobOffers" +  (int)"CurrentJob" is less than two, it means that not enough jobs to compare(this includes current job and job offer).


2. When choosing to enter current job details, a user will:
a. Be shown a user interface to enter (if it is the first time) or edit all the details of
their current job, which consist of:
i. Title
ii. Company
iii. Location (entered as city and state)
iv. Yearly salary adjusted for cost of living
v. Yearly bonus adjusted for cost of living
vi. Leave time (in days)
vii. Number of stock option shares offered
viii. Home Buying Program fund (one-time dollar amount up to 15% of Yearly Salary)
ix. Wellness Fund ($0 to $5,000 inclusive annually)
b. Be able to either save the job details or cancel and exit without saving, returning
in both cases to the main menu

Solution: 

(a) Which interface to show, enter current job interface or edit current job interface, is handled by "CurrentJob" attribute and "CurrentJobAvailable" method in "Menu" class. See Solution to 1.(1) for explanation. Moreover, these two interfaces should be the same in format. The only difference would be if the current job information is displayed or not. For enter job interface, the current job details are empty as no current job entered. Text fields corresponding to job details are (assigned) empty.
For edit current job interface, the current job information are displayed by assigning values to text fields from existing current job instance.

(b)There would be constructor “Job” in "Job" class. If "save and return to main menu" is chosen, the constructor "Job" will be called to create the current job instance and "JobScore" attribute will be set to 0 as default. Meanwhile,"CurrentJob" is set to be true. Then, the displayed interface will be set to main menu. If "exit without saving" is clicked, simply set the displayed interface to main menu. The entered information is not saved. If "edit current job" interface is shown, the "save" is achieved by "EditJob" method which re-assign the values of Job attributes.


3. When choosing to enter job offers, a user will:
a. Be shown a user interface to enter all the details of the offer, which are the same
ones listed above for the current job.
b. Be able to either save the job offer details or cancel.
c. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the
offer (if they saved it) with the current job details (if present).

Solution: 

(a) Once clicked "enter job offers", the displayed interface will first set to the interface for "enter job offers". This interface will be similar to "enter current job" interface (same fields would be listed to user to fill) with different operation options(or buttons). 

(b) If "save" is chosen, the constructor "Job" will be called to create the current job instance and "JobScore" attribute will be set to 0 as default. Meanwhile,1) "NoOfJobOffers" is increased by 1 and 2)"compare offer with current job" button will be enabled. If "cancel" is chosen, GUI will display the main menu without saving the entered job offer.

(c).(1) To enter another offer, GUI simply set text fields of "enter job offer" interface to empty.

(c).(2) To return to main menu, GUI will display main menu.

(c).(3) To achieve this, "CompareJob" method will be called with the first argument to be current job instance and second argument to be newly saved job offer. This button should be disabled by default until new job offer is saved. "CompareJob" method will display the details of current job and the newly saved job offer.


4. When adjusting the comparison settings, the user can assign integer weights to:
a. Yearly salary
b. Yearly bonus
c. Leave time
d. Number of shares offered
e. Home Buying Program Fund
f. Wellness Fund
If no weights are assigned, all factors are considered equal.

Solution:

There is a "Weight" class, including the attributes of the listing factors. When the app is initialized, the constructor method "Weight()" would run with it, by setting all of attributes with default integer value of 1, to make sure all factors could be equal, as well as the hidden goal of there is always an existing weight for the App, during its lifetime.
Other than the overridden default constructor, a method of  "ChangeWeights" is provided to set different factors different weight. Once being set, the "Weight" attribute in Menu class would be set as requested.


5. When choosing to compare job offers, a user will:
a. Be shown a list of job offers, displayed as Title and Company, ranked from best
to worst (see below for details), and including the current job (if present), clearly
indicated.
b. Select two jobs to compare and trigger the comparison.
c. Be shown a table comparing the two jobs, displaying, for each job:
i. Title
ii. Company
iii. Location
iv. Yearly salary adjusted for cost of living
v. Yearly bonus adjusted for cost of living
vi. Leave time
vii. Number of shares offered
viii. Home Buying Program fund (one-time up to 15% of Yearly Salary)
ix. Wellness Fund fund ($0 to $5,000 inclusive annually)
d. Be offered to perform another comparison or go back to the main menu.

Solution:

(a) After prompts are handled within the GUI the "GetJobScore" method will calculate and assign "JobScore" for all stored jobs. Then, the "RankJob()" method would sort all stored jobs in the "jobs" list/array/map, by "JobScore" attribute then rank them from high to low. If "CurrentJob" is true, the current job would be shown and highlighted. Only current job would be highlighted.

(b)&(c) The GUI would allow user to select the ranked listing job options. "CompareJob" method will be invoked to compare two specific job, after selecting two jobs, a new interface would pop up showing the two offers' information, by utilizing getters.

(d) The GUI would supply two buttons letting user to choose either be back to main menu or continue to pick another two jobs, two different interfaces would be linked to the buttons.

6. When ranking jobs, a job’s score is computed as the weighted sum of:
AYS + AYB + (LT * AYS / 260) + (CSO/2) + HBP + WF
where:
AYS = yearly salary adjusted for cost of living
AYB = yearly bonus adjusted for cost of living
LT = leave time
CSO = Company shares offered (assuming a 2-year vesting schedule and a
price-per-share of $1)
HBP = Home Buying Program
WF= Wellness Fund

Solution:

This computation would be represented within the “GetJobScore” method, in the Job class. When jobs/offers are entered, current Weights(Weight) would be read and used to calculate their scores, by the given formula.

7. The user interface must be intuitive and responsive.

Solution:

This would not be reflected in this design since it is within the GUI design and implementation.

8. For simplicity, you may assume there is a single system running the app (no
communication or saving between devices is necessary).

Solution: 

The design above is only considering the use in a single system running the app. No interaction or data transfer function module is considered.  


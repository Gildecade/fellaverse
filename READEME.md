
![screenshot](title.png)

# Project： Fellaverse
## 1. Problem Description
A group of fellas from the ECE department of University of Waterloo want to find a way to practice their programming skills. In our spare time, we often go to the gym four times a week with different training targets, and sometime, we forget the weight that we set before for a specific equipment. Also, to make the training more effective, there is a need to make the numbers competitive and let the other fellas know about it. So, Fellaverse comes to play here, which is a cross-platform application that allows a user to track his/her fitness record and notify other fellas. The detail description of Fellaverse is discussed in section 2 of this document. 

The main features of the Fellaverse include:
- the ability to create/read/update/delete fitness schedule
- the ability to create/read/update/delete fitness record
- the ability to set a time off if in vacation or sickness
- the ability to perform deep analysis with the fitness data by using the weight changes, heart rate or time, and also show the result by some graphs such as line or bar graph
- the ability to communicate with other users using gif and emoji
- the ability to manage system preferences 
- the ability to view/purchase virtual fitness classes 

Optionals:
- the ability to view/upload/delete training video as a coach
- the ability to analyze fitness data like heart rate using ML or DL
- the ability to notify user by sending email or showing notification in the mobile app
- the ability to analyze user comments by NLP 

## 2. Features
Fellaverse supports four categories of users: anonymous user, trainees, coaches and system administrators.

Anonymous users only have view access to the system. Anonymous users can see any fitness schedule from any trainee if they are interested, but they can't create/modify any fitness record/schedule. Anonymous can also chat with the other trainees, but such comments should be marked with a special flag.  

Trainees can create new fitness schedule in a weekly basis, and modify/delete it if needed. For example, trainees can create a schedule targeting back muscles in the next 10 weeks on Thursday. In addition, trainees can set their exercises in each training session. As for the fitness record, trainees can update the weight they use for the current time and the number of sets they did. If needed, trainees can see the instruction videos from coach if they forgot how to do it (Optional). For unpredictable reason that trainees miss the session, they should be able to request a time-off. What's more, trainees can see their weight trend by some graphs to see how they improve in the pass certain time. Communication is also possible within the app, bluffing is encouraged between trainees to enforce the competition. Trainees can also send any gif or emoji they find online to the other trainees. 

Coaches can do anything that trainees can do, and also view/upload/delete training videos (Optional).

System administrators can manage all the user related operations and also can be acted as a coach to monitor all the trainees and upload training videos to the system (Optional). In addition, only the administrators can invite user to the system and assign trainee to the coach. The invitation should be constructed with an email including the link to the website. 

## 3. Technical Specification
The backend should use JAVA with the [Spring](https://spring.io/quickstart) framework. The frontend should use [React](https://reactjs.org/docs/getting-started.html). The target UI component library is [Material UI](https://mui.com/material-ui/getting-started/overview/). We will follow the [agile](https://asana.com/resources/agile-methodology?utm_campaign=NB--NAMER--EN--Catch-All--All-Device--DSA&utm_source=google&utm_medium=pd_cpc_nb&gclid=CjwKCAjwzY2bBhB6EiwAPpUpZm-EEHhhibr3UUiEFmIjURx6f9L_AAyBwr2TjHagW0p71OylaFbJtxoCTVcQAvD_BwE&gclsrc=aw.ds) software developing management system, and use git for version controlling. In particular, we might use [Jira](https://www.atlassian.com/software/jira?&aceid=&adposition=&adgroup=103971651024&campaign=9521086470&creative=536313571844&device=c&keyword=agile&matchtype=e&network=g&placement=&ds_kids=p52018173426&ds_e=GOOGLE&ds_eid=700000001558501&ds_e1=GOOGLE&gclid=CjwKCAjwzY2bBhB6EiwAPpUpZmGPwEe3TB9Zxq0FKUb4d3_s4n_rhpfCMiR30g30tfUlJbFUaExsIhoCpz8QAvD_BwE&gclsrc=aw.ds) or [Azure devops](https://azure.microsoft.com/en-us/products/devops/) to manage the project. The target database system is MySQL, and we will use [Hibernate](https://docs.jboss.org/hibernate/orm/6.1/quickstart/html_single/#_maven_repository_artifacts) as an ORM to manage all the created entities from the backend system. All source code should follow standard UNIX programming conventions and using advanced OO programming techniques such as polymorphism and design patterns. 

[IDEA](https://www.jetbrains.com/idea/) is prefered here in terms of IDE. 

### 3.1 User Interface (UI)
Fellaverse user interface will preferably be both in web page and in IOS app. Both of them should have the same functionalities and share the same database storage. In general, user features should be easily navigable, either as menu items and/or pop-up menus. The look-and-feel of the system should be professional and consistent with commercially available UIs. 

### 3.2 Data Storage
Fellaverse will have a central database which contains all the user information. 

### 3.3 Delivery and Deployment
Fellaverse should have 3 

Fellaverse should be delivered in a straightforward automatic pipeline. 


which is meant to create an interactive fun community for all the fellas. As the first step, a gym community will be implemented for everyone who wants to learn how to work out and mark their fitness records.  

The ultimate goal of Fellaverse is to build an interactive fun community for all the fellas. We might potentially add a community related to Japanese anime. 

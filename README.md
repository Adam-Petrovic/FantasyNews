
# Fantasy News App
## CSC207 Final Project
### Contributor: Alexandros Pratsos (pratsosa), Adam Petrovic (Adam-Petrovic), Evelyn Hughes (EveHughes), Hugo Fuhrer (Hugo-fuhrer), Yibing Xia (jennifer1046)

### Description 
Users are able to sign up and login to the fantasy news app and play solo or with friends in a league. 

In solo play, users can track in real time how well their selected words are trending.

In league, their drafted words will be compared aginst others and a winner will be declared at the end of each day.

Users are also able to add friends and view live and historical rankings for any given league.

---

### Table of Contents
1. [Description](#description)
2. [Features](#features)
3. [Installation](#installation)
4. [Usage](#usage)
5. [Feedback and Contribution](#feedback-and-contribution)

---

### Features

1. **User Story 1: Log in/out, Solo Play: Adam Petrovic**  
   Users are able to sign up, log in, or log out of their account. 
   After logging in, users are able to edit the words in their solo play and view the updated points live.

2. **User Story 2: Create league: Evelyn Hughes**  
   Users can create any non pre-existing league and join any existing league with the correct id.

3. **User Story 3: Add friends: Yibing Xia**  
   Users may add other users as friends. Optionally, users can click on a friend to see a 1v1 comparison between their points; results include win, lose, or tie.

4. **User Story 4: Drafting: Alexandros Pratsos**  
   

5. **User Story 5: Rankings: Hugo Fuhrer**  
   Users can track rankings for any league in real time while also seeing how each league player has done historically.

6. **User Story 6: Store Data: Team**  
   
---

### Installation
1. Clone the repository:
   ```bash
   git clone <https://github.com/Adam-Petrovic/FantasyNews.git>
   ```
2. Install java
3. Open the project in your IDE of choice

---

### Usage
1. Run main:
2. Sign up and log in.
---

### License
MIT license.

--




Alexandros Pratsos - Pratsosa  
Adam Petrovic - Adam-Petrovic  
Evelyn Hughes - EveHughes  
Hugo Fuhrer - Hugo-fuhrer  
Yibing Xia - jennifer1046  

Our group task is task 1  

Alexandros Pratsos is completing task 2  
Adam Petrovic is completing task 3  
Evelyn Hughes is completing task 4  
Hugo Fuhrer is completing task 5  
Yibing Xia is completing task 6  

Tasks/Project Blueprint can be found in this [link](https://docs.google.com/document/d/1Q1Z9kBl2Huhp6V3-i3qPANTCCzPoLs2BD_COMZS6cts/edit?usp=sharing)

Updates for week 10:
Alex - Implemented PantryDAO (a user data access object which stores data in cloud) and integrated it with soloPlay. Also created a unit test for addWord use case  
Adam - Added GuardianAPI to update points, integrated with user data access objects. Added LeagueView. Fixed updatePoints use case. Also created unit test for updatePoints.

# Boruto API Project With Kotlin

## Overview
The **Boruto API** is a RESTful API designed to provide data about characters from the anime *Boruto: Naruto Next Generations*. This project demonstrates the use of **Ktor**, a lightweight framework for building asynchronous servers in Kotlin, and **PostgreSQL**, a powerful open-source relational database system. Currently, the API offers a single feature: fetching data about characters in the anime.

---

## Features
### 1. **Get Hero**
   - **Endpoint:** `/boruto/heroes`
   - **Description:** Fetches a list of characters from the *Boruto* anime stored in the database.
   - **Pagination Support:** Allows retrieving characters in pages for better data handling.
   - **Response Example:**
     ```json
        {
            "success": true,
            "message": "Fetched heroes successfully",
            "prevPage": null,
            "nextPage": 2,
            "heroes": [
                {
                    "id": 1,
                    "name": "Sasuke",
                    "image": "/images/sasuke.jpg",
                    "about": "Sasuke Uchiha (うちはサスケ, Uchiha Sasuke) is one of the last surviving members of Konohagakures Uchiha clan. After his older brother, Itachi, slaughtered their clan, Sasuke made it his mission in life to avenge them by killing Itachi. He is added to Team 7 upon becoming a ninja and, through competition with his rival and best friend, Naruto Uzumaki.",
                    "rating": 5.0,
                    "power": 98,
                    "month": "July",
                    "day": "23rd",
                    "family": [
                        "Fugaku",
                        "Mikoto",
                        "Itachi",
                        "Sarada",
                        "Sakura"
                    ],
                    "abilities": [
                        "Sharingan",
                        "Rinnegan",
                        "Sussano",
                        "Amateratsu",
                        "Intelligence"
                    ],
                    "natureTypes": [
                        "Lightning",
                        "Fire",
                        "Wind",
                        "Earth",
                        "Water"
                    ]
                },
                {
                    "id": 2,
                    "name": "Naruto",
                    "image": "/images/naruto.jpg",
                    "about": "Naruto Uzumaki (うずまきナルト, Uzumaki Naruto) is a shinobi of Konohagakure Uzumaki clan. He became the jinchūriki of the Nine-Tails on the day of his birth — a fate that caused him to be shunned by most of Konoha throughout his childhood. After joining Team Kakashi, Naruto worked hard to gain the villages acknowledgement all the while chasing his dream to become Hokage.",
                    "rating": 5.0,
                    "power": 98,
                    "month": "Oct",
                    "day": "10th",
                    "family": [
                        "Minato",
                        "Kushina",
                        "Boruto",
                        "Himawari",
                        "Hinata"
                    ],
                    "abilities": [
                        "Rasengan",
                        "Rasen-Shuriken",
                        "Shadow Clone",
                        "Senin Mode"
                    ],
                    "natureTypes": [
                        "Wind",
                        "Earth",
                        "Lava",
                        "Fire"
                    ]
                },
                {
                    "id": 3,
                    "name": "Sakura",
                    "image": "/images/sakura.jpg",
                    "about": "Sakura Uchiha (うちはサクラ, Uchiha Sakura, née Haruno (春野)) is a kunoichi of Konohagakure. When assigned to Team 7, Sakura quickly finds herself ill-prepared for the duties of a shinobi. However, after training under the Sannin Tsunade, she overcomes this, and becomes recognised as one of the greatest medical-nin in the world.",
                    "rating": 4.5,
                    "power": 92,
                    "month": "Mar",
                    "day": "28th",
                    "family": [
                        "Kizashi",
                        "Mebuki",
                        "Sarada",
                        "Sasuke"
                    ],
                    "abilities": [
                        "Chakra Control",
                        "Medical Ninjutsu",
                        "Strength",
                        "Intelligence"
                    ],
                    "natureTypes": [
                        "Earth",
                        "Water",
                        "Fire"
                    ]
                },
                {
                    "id": 4,
                    "name": "Boruto",
                    "image": "/images/boruto.png",
                    "about": "Boruto Uzumaki (うずまきボルト, Uzumaki Boruto) is a shinobi from Konohagakures Uzumaki clan and a direct descendant of the Hyūga clan through his mother. While initially resentful of his father and his absence since becoming Hokage, Boruto eventually comes to respect his father and duties.",
                    "rating": 4.9,
                    "power": 95,
                    "month": "Mar",
                    "day": "27th",
                    "family": [
                        "Naruto",
                        "Hinata",
                        "Hanabi",
                        "Himawari",
                        "Kawaki"
                    ],
                    "abilities": [
                        "Karma",
                        "Jogan",
                        "Rasengan",
                        "Intelligence"
                    ],
                    "natureTypes": [
                        "Lightning",
                        "Wind",
                        "Water"
                    ]
                },
                {
                    "id": 5,
                    "name": "Sarada",
                    "image": "/images/sarada.jpg",
                    "about": "Sarada Uchiha (うちはサラダ, Uchiha Sarada) is a kunoichi from Konohagakures Uchiha clan. Because she was raised only by her mother without having her father around, Sarada initially struggles to understand who she is or what shes supposed to be. After meeting him with the help of Naruto Uzumaki, Sarada comes to believe that she is defined by the connections she has with others, and as a member of Team Konohamaru, she seeks to someday become Hokage so that she can connect with as many people as possible.",
                    "rating": 4.9,
                    "power": 95,
                    "month": "Mar",
                    "day": "31st",
                    "family": [
                        "Sasuke Uchiha",
                        "Sakura Uchiha"
                    ],
                    "abilities": [
                        "Sharingan",
                        "Strength",
                        "Intelligence"
                    ],
                    "natureTypes": [
                        "Lightning",
                        "Wind",
                        "Fire"
                    ]
                }
            ]
        }
     ```


     ### 2. **Search Hero By Name**
   - **Endpoint:** `/boruto/heroes/search?name={name}`
   - **Description:** Allows users to search for heroes by their name. can partially match the name.
   - **Pagination Support:** Allows retrieving characters in pages for better data handling.
- **Query Parameter:**
    - `name` (required): A string to search for in hero names.
- **Response Example:**
```json
    {
    "success": true,
    "message": "Search completed successfully",
    "prevPage": null,
    "nextPage": null,
    "heroes": [
        {
            "id": 1,
            "name": "Sasuke",
            "image": "/images/sasuke.jpg",
            "about": "Sasuke Uchiha (うちはサスケ, Uchiha Sasuke) is one of the last surviving members of Konohagakures Uchiha clan. After his older brother, Itachi, slaughtered their clan, Sasuke made it his mission in life to avenge them by killing Itachi. He is added to Team 7 upon becoming a ninja and, through competition with his rival and best friend, Naruto Uzumaki.",
            "rating": 5.0,
            "power": 98,
            "month": "July",
            "day": "23rd",
            "family": [
                "Fugaku",
                "Mikoto",
                "Itachi",
                "Sarada",
                "Sakura"
            ],
            "abilities": [
                "Sharingan",
                "Rinnegan",
                "Sussano",
                "Amateratsu",
                "Intelligence"
            ],
            "natureTypes": [
                "Lightning",
                "Fire",
                "Wind",
                "Earth",
                "Water"
            ]
        },
        {
            "id": 3,
            "name": "Sakura",
            "image": "/images/sakura.jpg",
            "about": "Sakura Uchiha (うちはサクラ, Uchiha Sakura, née Haruno (春野)) is a kunoichi of Konohagakure. When assigned to Team 7, Sakura quickly finds herself ill-prepared for the duties of a shinobi. However, after training under the Sannin Tsunade, she overcomes this, and becomes recognised as one of the greatest medical-nin in the world.",
            "rating": 4.5,
            "power": 92,
            "month": "Mar",
            "day": "28th",
            "family": [
                "Kizashi",
                "Mebuki",
                "Sarada",
                "Sasuke"
            ],
            "abilities": [
                "Chakra Control",
                "Medical Ninjutsu",
                "Strength",
                "Intelligence"
            ],
            "natureTypes": [
                "Earth",
                "Water",
                "Fire"
            ]
        },
        {
            "id": 5,
            "name": "Sarada",
            "image": "/images/sarada.jpg",
            "about": "Sarada Uchiha (うちはサラダ, Uchiha Sarada) is a kunoichi from Konohagakures Uchiha clan. Because she was raised only by her mother without having her father around, Sarada initially struggles to understand who she is or what shes supposed to be. After meeting him with the help of Naruto Uzumaki, Sarada comes to believe that she is defined by the connections she has with others, and as a member of Team Konohamaru, she seeks to someday become Hokage so that she can connect with as many people as possible.",
            "rating": 4.9,
            "power": 95,
            "month": "Mar",
            "day": "31st",
            "family": [
                "Sasuke Uchiha",
                "Sakura Uchiha"
            ],
            "abilities": [
                "Sharingan",
                "Strength",
                "Intelligence"
            ],
            "natureTypes": [
                "Lightning",
                "Wind",
                "Fire"
            ]
        }
    ]
}
```

---

## Technology Stack
### 1. **Backend Framework**
   - **Ktor**: Used to build the REST API with asynchronous capabilities for handling HTTP requests efficiently.
   
### 2. **Database**
   - **PostgreSQL**: A relational database system used to store and manage data about Boruto characters.

### 3. **Serialization**
   - **Kotlinx Serialization**: Handles the conversion of data between JSON and Kotlin objects seamlessly.

### 4. **ORM**
   - **Exposed**: An ORM library for managing PostgreSQL queries and interactions.

---


## API Endpoints
| Method | Endpoint      | Description                    |
|--------|---------------|--------------------------------|
| GET    | `/boruto/heroes`     | Retrieves a paginated list of heroes. |
| GET    | `/boruto/heroes/search?name={name}`| Fetches details of a specific hero by ID (Future Implementation). |

---

## Future Enhancements
- Add support for creating, updating, and deleting heroes.
- Implement user authentication for secure API access.
- Add more anime-related data like episodes and missions.
- Improve search and filtering capabilities.

---

## Contribution
Feel free to fork this repository and submit pull requests. Contributions are welcome to expand the functionality and improve the API.

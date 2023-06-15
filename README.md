The Digital Music Service is a web application built using the Spring framework and Hibernate. It provides a platform for users to access and explore a vast catalog of songs, albums, and artists. The application integrates with external services such as SendGrid and Twilio for email notifications and SMS alerts when users purchase subscriptions, DALL·E 2 for single/album covers. Additionally, it leverages the Spotify API to gather metadata and information about each song and album.

Features
* User Registration and Authentication: Users can create accounts, log in, and manage their profiles.
* Artist Registration and Authentication: Artists can create accounts, log in, and manage their profiles.
* Open AI API with DALL·E 2 model for artists where a single/album cover can be generated easily with a prompt.
* Song and Album Search: Users can search for songs and albums based on various criteria, such as title, artist, or genre.
* Playlists: Users can create and manage personalized playlists and add songs.
* Recommendations: The application provides personalized song recommendations based on users' listening history and preferences.
* Subscription Management: Users can subscribe to different tiers of service, unlocking additional features and benefits.
* Email Notifications: SendGrid integration enables email notifications for account creation, subscription updates, and more.
* SMS Alerts: Twilio integration enables SMS alerts for subscription purchases, upcoming events, and special offers.
* Social Sharing: Users can share their favorite songs, playlists, and artists on social media platforms. //TBD
* User Activity Tracking: The application tracks user activity and provides insights into listening habits and trends. //TBD

Technologies Used
* Spring Framework:Provides a robust and scalable backend structure for the application.
* Hibernate: Offers Object-Relational Mapping (ORM) capabilities for seamless interaction with the database.
* Spotify API: Accesses a vast music catalog, gathering metadata and information about songs and albums.
* SendGrid: Sends transactional emails for user notifications and account management.
* Twilio: Enables SMS alerts and notifications for subscription-related events and also password change requests.
* DALL·E 2 generative images for artists, album/single cover creation.
* ULIDs (Universally Unique Lexicographically Sortable Identifiers) for generating unique and sortable identifiers for various entities and records within the system.

Getting Started
1. Clone the repository.
2. Configure the application properties for database connection, external service credentials, and API keys.
3. Build and run the application using your preferred IDE.
4. Contributions are welcome.


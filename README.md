## Overview

This project contains a video rental API based on Spring Web and Hibernate.
Business logic consists of the following entities (detailed mapping can be found in the Database section below):
- Groups (used for grouping Customers, groups contain discounts applied when renting Copies of Videos)
- Customers (ability to rent Copies, thus creating new Rentals)
- Categories (used for grouping Videos; categories contain default rental period, base fees and penalty levels)
- Videos (a video contains title, year and director, Copies of Videos can be created)
- Copies (ability to be rented by Customers, thus creating new Rentals)
- Rentals (records created when renting a Copy by a Customer, contain start-, due- and return dates, fees and penalties; returning the Copy and settling (paying for) a Rental is handled separately)

## Client

Dedicated React client: https://github.com/MateuszToczyski/video-rental-react-client

## Database

ERD: https://github.com/MateuszToczyski/video-rental-spring-api/blob/master/DB%20mapping.png

Database setup:
- engine: MySQL
- schema: videos
- username: videos
- password: video

The project uses Hibernate with code-first approach.

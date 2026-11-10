use accommodationdb;


-- Create the Account table based on Account.java
-- accountNumber is a 10-character string, primary key, and cannot be null[cite: 1]
CREATE TABLE account (
    account_number VARCHAR(10) NOT NULL PRIMARY KEY,
    mailing_address VARCHAR(255),
    email_address VARCHAR(255),
    phone_number VARCHAR(50)
);

-- Create the Reservation table based on Reservation.java and its subclasses
-- Uses SINGLE_TABLE inheritance with a reservation_type discriminator column[cite: 5]
CREATE TABLE reservation (
    reservation_number VARCHAR(10) NOT NULL PRIMARY KEY,
    reservation_type VARCHAR(31) NOT NULL, -- Discriminator column (CABIN, HOTEL, HOUSE)
    account_number VARCHAR(10),
    physical_address VARCHAR(255),
    mailing_address VARCHAR(255),
    start_date DATE,
    number_of_nights INT,
    number_of_beds INT DEFAULT 2,
    number_of_bedrooms INT DEFAULT 1,
    number_of_bathrooms INT DEFAULT 1,
    lodging_size_sq_ft INT,
    reservation_price DOUBLE,
    status VARCHAR(50) DEFAULT 'DRAFT',
    
    -- CabinReservation specific fields[cite: 2]
    full_kitchen BOOLEAN,
    loft BOOLEAN,
    
    -- HotelReservation specific fields[cite: 3]
    kitchenette BOOLEAN,
    
    -- HouseReservation specific fields[cite: 4]
    number_of_floors INT,
    
    FOREIGN KEY (account_number) REFERENCES account(account_number) 
        ON DELETE CASCADE
);
use accommodationdb;

-- Insert sample Accounts
INSERT INTO account (account_number, mailing_address, email_address, phone_number)
VALUES 
('A123456789', '123 Main St, Springfield', 'user1@email.com', '555-0100'),
('A987654321', '456 Oak Ave, Shelbyville', 'user2@email.com', '555-0200');

-- Insert sample CABIN Reservation[cite: 2]
INSERT INTO reservation (
    reservation_number, reservation_type, account_number, physical_address, 
    start_date, number_of_nights, lodging_size_sq_ft, status, 
    full_kitchen, loft
) VALUES (
    'R000000001', 'CABIN', 'A123456789', '10 Pine Trail', 
    '2026-12-01', 3, 850, 'DRAFT', 
    TRUE, TRUE
);

-- Insert sample HOTEL Reservation[cite: 3]
-- Note: Hotel reservations default to 1 bedroom, 1 bathroom, and 2 beds upon creation[cite: 3]
INSERT INTO reservation (
    reservation_number, reservation_type, account_number, physical_address, 
    start_date, number_of_nights, number_of_beds, number_of_bedrooms, number_of_bathrooms,
    lodging_size_sq_ft, status, kitchenette
) VALUES (
    'R000000002', 'HOTEL', 'A987654321', '200 Downtown Blvd', 
    '2026-12-15', 2, 2, 1, 1, 
    400, 'COMPLETED', TRUE
);

-- Insert sample HOUSE Reservation[cite: 4]
INSERT INTO reservation (
    reservation_number, reservation_type, account_number, physical_address, 
    start_date, number_of_nights, number_of_beds, number_of_bedrooms, number_of_bathrooms,
    lodging_size_sq_ft, status, number_of_floors
) VALUES (
    'R000000003', 'HOUSE', 'A123456789', '77 Maple Court', 
    '2026-12-20', 7, 4, 3, 2, 
    2100, 'DRAFT', 2
);
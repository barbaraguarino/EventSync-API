CREATE TABLE events (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    location VARCHAR(255),
    event_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    event_type VARCHAR(50) NOT NULL,
    ticket_url VARCHAR(255),
    official_site_url VARCHAR(255),
    online_url VARCHAR(255),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    category_id UUID NOT NULL,
    organizer_id UUID NOT NULL,

    CONSTRAINT fk_event_category FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT fk_event_organizer FOREIGN KEY (organizer_id) REFERENCES users(id)
);
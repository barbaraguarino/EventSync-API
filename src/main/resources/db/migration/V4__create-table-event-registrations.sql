CREATE TABLE event_registrations (
    event_id UUID NOT NULL,
    user_id UUID NOT NULL,

    PRIMARY KEY (event_id, user_id),

    CONSTRAINT fk_registration_event FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE,
    CONSTRAINT fk_registration_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
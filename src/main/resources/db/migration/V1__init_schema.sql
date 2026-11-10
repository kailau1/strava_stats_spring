CREATE TABLE public.strava_token
(
    id bigint NOT NULL,
    access_token character varying(255),
    expires_at timestamptz,
    refresh_token character varying(255),
    CONSTRAINT strava_token_pkey PRIMARY KEY (id)
);

CREATE TABLE public.strava_activity
(
    id bigint NOT NULL,
    average_speed double precision NOT NULL,
    average_watts double precision,
    distance double precision NOT NULL,
    max_speed double precision NOT NULL,
    moving_time integer NOT NULL,
    start_date timestamptz NOT NULL,
    total_elevation_gain double precision NOT NULL,
    sport_type TEXT NOT NULL,
    CONSTRAINT strava_activity_pkey PRIMARY KEY (id)
);

CREATE INDEX idx_strava_activity_start_date ON strava_activity (start_date);
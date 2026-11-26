CREATE TABLE IF NOT EXISTS vehiculos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    placa TEXT NOT NULL UNIQUE,
    propietario TEXT NOT NULL,
    tipo TEXT NOT NULL,
    hora_ingreso TEXT NOT NULL,
    horas INTEGER NOT NULL,
    tarifa REAL NOT NULL
);

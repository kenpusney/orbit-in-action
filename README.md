# Orbit in action

## Getting started

First you should setup an orbit server on your local, usually it would be convenient using `docker-compose`:

```yaml
version: '3.1'
services:
  # orbitframework/orbit:2.0.0-alpha.108
  orbit-server:
    image: orbitframework/orbit:2.0.0-alpha.108
    restart: always
    ports:
      - 50056:50056
    networks:
      - dev
networks:
  dev:
    external: true
```

Then you can start build your apps using orbit.

The official tutorials have incorrect examples, I've fixed in this repo.

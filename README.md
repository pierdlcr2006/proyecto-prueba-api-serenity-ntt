# Proyecto de pruebas API

## API utilizada

Se utiliza [JSONPlaceholder](https://jsonplaceholder.typicode.com/), una API
gratuita para prácticas y pruebas.

Endpoints probados:

- `GET /posts`: consulta la lista de posts.
- `GET /posts/1`: consulta un post existente.
- `POST /posts`: crea un post simulado.
- `GET /posts/101`: valida el caso de un post inexistente.

Los datos del `POST` se envían desde una `DataTable` definida en el feature de
Gherkin.

## Estructura

```text
src/test/java/com/ntt/proyecto/
├── runners/
├── stepdefinitions/
├── tasks/
├── questions/
├── apis/
└── models/

src/test/resources/
├── features/
├── data/
└── serenity.conf
```

## Ejecución

Desde la raíz del proyecto:

```bash
mvn clean verify
```

El reporte Serenity queda generado en:

```text
target/site/serenity/index.html
```

La API es simulada: el `POST` responde como si creara el recurso, pero no lo
guarda de forma permanente.

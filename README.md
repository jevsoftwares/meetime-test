# HubSpot Integration - Meetime Test

Este projeto é responsável pela integração com o HubSpot, permitindo autenticação via OAuth2 e operações de CRUD para contatos.

## 🔧 Configuração do HubSpot

Para utilizar a integração, siga os passos abaixo para criar um aplicativo no HubSpot:

### 1️⃣ Criar uma conta de desenvolvedor

1. Acesse [HubSpot Developer](https://developers.hubspot.com/)
2. Clique em **Get started** e crie sua conta de desenvolvedor.

### 2️⃣ Criar um aplicativo no HubSpot

1. Após acessar sua conta de desenvolvedor, vá para **Apps** e clique em **Create App**.
2. Preencha os detalhes do aplicativo e configure as permissões (scopes):
    - `crm.objects.contacts.read`
    - `crm.objects.contacts.write`
    - `crm.schemas.contacts.write`
3. Na seção **Auth settings**, configure a **Redirect URL** para:
   ```
   http://localhost:8999/hubspot/v1/oauth/callback
   ```
   **⚠ Atenção:** O HubSpot exige que a URL de Webhooks seja HTTPS, então se precisar processar Webhooks, seu endpoint precisa estar exposto publicamente via HTTPS.

### 3️⃣ Obter credenciais do aplicativo

1. Após criar o app, copie as seguintes informações:
    - **Client ID**
    - **Client Secret**
    - **Redirect URI**
2. Configure essas informações nas variáveis de ambiente conforme explicado abaixo.

## 🚀 Configuração e Execução do Serviço

### 📌 Variáveis de Ambiente

Antes de iniciar o serviço, configure as seguintes variáveis de ambiente:

```properties
spring.application.name=meetime-test
server.servlet.context-path=/hubspot
server.port=8999

hubspot.client-id=${HUBSPOT_CLIENT_ID:}
hubspot.client-secret=${HUBSPOT_CLIENT_SECRET:}
hubspot.redirect-uri=${HUBSPOT_REDIRECT_URI:http://localhost:8999/hubspot/v1/oauth/callback}
hubspot.token-uri=${HUBSPOT_TOKEN_URI:https://api.hubapi.com/oauth/v1/token}
hubspot.auth-uri=${HUBSPOT_AUTH_URI:https://app.hubspot.com/oauth/authorize}

config.apikey=${CONFIG_X_API_KEY:xpto}
```

### 📌 Executando o serviço

1. Clone este repositório:
   ```sh
   git clone <url_do_repositorio>
   ```
2. Acesse a pasta do projeto:
   ```sh
   cd meetime-test
   ```
3. Compile e execute o projeto usando Maven:
   ```sh
   mvn spring-boot:run
   ```

## 🛠 Endpoints Disponíveis

O serviço expõe endpoints para autenticação e gerenciamento de contatos no HubSpot.

### 🔑 Autenticação

- **Login no HubSpot:** `GET /v1/auth/login`
- **Callback OAuth2 (oculto):** `GET /v1/oauth/callback`

### 👥 Contatos

- **Criar contato:** `POST /v1/contact`
- **Listar contatos:** `GET /v1/contacts`

## 📖 Documentação da API

Após iniciar o serviço, a documentação pode ser acessada via Swagger UI:

```
http://localhost:8999/hubspot/swagger-ui/index.html
```

## 📌 Observações Importantes

- A API do HubSpot exige que a URL de Webhooks seja **HTTPS**.
- O callback de OAuth2 funciona localmente, mas para Webhooks externos, o serviço precisa estar exposto publicamente.

---

Com essas configurações, você pode autenticar usuários no HubSpot, listar e criar contatos de maneira segura e eficiente! 🚀


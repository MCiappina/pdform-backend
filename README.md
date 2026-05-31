# PDForms - Gerador de Anexos PDF

Este projeto é um sistema que gera documentos PDF (Anexos) de forma dinâmica e com formatação perfeita a partir de formulários web.

O sistema é dividido em dois repositórios:

- **Backend** (Este repositório): API em Spring Boot que processa os dados e gera o PDF.
- **Frontend**: Interface em HTML/CSS/JS puro para preenchimento dos dados.

O backend utiliza **Thymeleaf** para processar os templates HTML e o **Microsoft Playwright** (Chromium headless) para renderizar CSS moderno (como Flexbox) e exportar o PDF final com alta fidelidade visual.

---

## 🛠️ Tecnologias Utilizadas

- **Backend:** Java 17, Spring Boot, Maven
- **Renderização de PDF:** Microsoft Playwright para Java, Thymeleaf
- **Frontend:** HTML5, CSS3, JavaScript Vanilla (Fetch API)

---

## 📋 Pré-requisitos

Antes de rodar o projeto, certifique-se de ter instalado em sua máquina:

- [Java 17+](https://www.oracle.com/java/technologies/downloads/)
- [Maven](https://maven.apache.org/download.cgi)
- Editor de código com servidor local para o frontend (recomendado: **VS Code** com a extensão **Live Server**).

---

## 🚀 Como Executar o Backend (Spring Boot)

### 1. Clone o repositório do backend

```bash
git clone https://github.com/MCiappina/pdform-backend.git
cd pdform-backend
```

### 2. Baixe as dependências e limpe o projeto

```bash
mvn clean install
```

### 3. Inicie o servidor Spring Boot

```bash
mvn spring-boot:run
```

O servidor estará rodando em `http://localhost:8080`.

> ⚠️ **AVISO IMPORTANTE SOBRE A PRIMEIRA EXECUÇÃO!**
>
> A primeira vez que você rodar o projeto e tentar gerar um PDF, o sistema pode parecer travado por cerca de **30 a 60 segundos**. Isso é normal. O Playwright está baixando os binários do navegador Chromium em segundo plano para conseguir renderizar as páginas. As gerações seguintes serão quase instantâneas.

---

## 💻 Como Executar o Frontend

O frontend é um projeto separado contendo apenas arquivos estáticos.

### Passo a passo:

1. Clone o repositório do frontend em uma pasta separada.
2. Abra a pasta do frontend no seu editor de código (ex: VS Code).
3. Certifique-se de que o **Backend já está rodando** (Passo 3 acima).
4. Clique com o botão direito no arquivo `index.html` (ou no arquivo do Anexo específico) e selecione **"Open with Live Server"**.
5. O frontend abrirá no seu navegador (geralmente em `http://127.0.0.1:5500`).

Como o backend já está configurado com `@CrossOrigin`, o frontend conseguirá se comunicar com a API local (porta `8080`) sem erros de CORS.

---

## 🗂️ Estrutura Principal do Backend

```
src/
└── main/
    ├── java/.../
    │   ├── controller/   ➔ Endpoints da API (PdfFormController.java)
    │   └── service/      ➔ Lógica do Playwright para conversão HTML → PDF (PdfGenerationService.java)
    └── resources/
        ├── templates/    ➔ Templates HTML/Thymeleaf dos anexos (ex: anexo-viii.html)
        └── static/
            └── images/   ➔ Imagens estáticas e logos usados nos cabeçalhos dos PDFs
```

---

## 🔌 Exemplo de Endpoint da API

A geração do PDF é feita enviando os dados do formulário via método **POST** para a rota específica do anexo.

```http
POST http://localhost:8080/api/pdf/gerar/viii
```

### Exemplo de Payload (JSON):

```json
{
  "faculdade": "Zona Leste",
  "edital_num": "001",
  "edital_ano": "2026",
  "statusConformidade": "CONFORME",
  "presidente_name": "João Silva",
  "justificativa": ""
}
```

### Resposta:

Arquivo binário `application/pdf` pronto para download.

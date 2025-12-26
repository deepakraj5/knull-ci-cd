# KnullCI – The Open Source CI/CD Platform

![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Java](https://img.shields.io/badge/Java-21-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.1-green.svg)
![GraalVM](https://img.shields.io/badge/GraalVM-Native-orange)

> **A modern, lightweight, and completely open-source CI/CD platform built for performance and simplicity.**

KnullCI is designed to provide developers with a robust, self-hosted alternative to heavy CI systems. Built with **Java 21** and **Spring Boot**, it offers lightning-fast build executions, real-time monitoring, and a beautiful UI powered by **Thymeleaf** and **Tailwind CSS**.

---

## 🚀 Why KnullCI?

- **Open Source at Heart**: Fully open code, zero vendor lock-in, and community-driven.
- **Blazing Fast**: Supports **native compilation** with GraalVM for <0.1s startup times.
- **Modern Stack**: Leveraging the latest in Java ecosystem—Spring Boot 4, gRPC, and Virtual Threads (Project Loom ready).
- **Secure by Default**: AES-256 encryption for all credentials and tokens.
- **Beautiful UX**: detailed build logs, live streaming updates, and a clean, dark-mode friendly interface.

## ✨ Key Features

- **GitHub Integration**: Seamless webhook integration triggers builds on push.
- **Pipeline as Code**: Define builds in a simple `.knull.yml` file within your repo.
- **Real-Time Visibility**: Live log streaming via SSE (Server-Sent Events).
- **Native Efficiency**: Run as a standalone native binary for minimal resource footprint.
- **Artifact Management** (Coming Soon): Store and retrieve build artifacts.

---

## 🛠️ Technology Stack

We believe in using the best tools for the job to ensure stability and speed.

- **Core**: Java 21, Spring Boot 4.0.1
- **Communication**: gRPC (v1.77.0) for internal services
- **Frontend**: Thymeleaf, Tailwind CSS, Vanilla JS
- **Build Tools**: Maven, GraalVM
- **Security**: Spring Security, AES-256

---

## ⚡ Getting Started

### Prerequisites

- **Java 21+**
- **Maven 3.8+**
- **Git**

### Installation

#### 1. Clone the Repository
```bash
git clone https://github.com/deepakraj5/knull-ci-cd.git
cd knull-ci-cd
```

#### 2. Configure Environment
Set up your encryption key (optional but recommended for security):
```bash
export KNULL_ENCRYPTION_SECRET_KEY="your-secure-32-char-key-here"
```

#### 3. Build & Run (JVM Mode)
```bash
mvn clean package -DskipTests
mvn spring-boot:run
```
Visit `http://localhost:8080` to see KnullCI in action.

#### 4. Build Native Image (Optional)
For extreme performance:
```bash
./build-native.sh
./target/knull
```
*Requires GraalVM JDK 21+.*

---

## 📖 Usage Guide

1.  **Login**: Default credentials are `knull` / `knull`.
2.  **Add a Credential**: Go to **Credentials**, add your GitHub Token (encrypted securely).
3.  **Create a Job**: Link a GitHub repository and point to your `.knull.yml`.
4.  **Trigger**: Push code or click "Trigger Build".

### Example `.knull.yml`

```yaml
name: Production Build
steps:
  - name: Install dependencies
    run:
      tool: npm
      args: ["install"]
  - name: Run Tests
    run:
      tool: npm
      args: ["test"]
```

---

## 🤝 Contributing

We love contributions! KnullCI is an open project and we welcome help to make it better.

1.  **Fork** the repository.
2.  **Create** a feature branch (`git checkout -b feature/amazing-feature`).
3.  **Commit** your changes.
4.  **Push** to the branch.
5.  **Open** a Pull Request.

Please read our [CONTRIBUTING.md](CONTRIBUTING.md) (coming soon) for details on our code of conduct and development process.

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

---

<p align="center">
  Built with ❤️ for the Open Source Community
</p>

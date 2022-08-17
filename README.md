
# Bem Estar Zupper

Bem Estar Zupper é um app de Android. Onde o objetivo é ajudar os Zuppers a se desconectarem do trabalho mais facilmente, para que possam focar em seu tempo de lazer e descanso, trazendo melhorias em sua saúde física e mental. E com isso teremos Zuppers mais felizes e saudáveis.

- Versão do App: 1.0

- Desenvolvido por Squad III (Catalisa  - Turma III)
## Screenshots

![App Screenshot](https://iili.io/gBOHS2.md.png)

## Protótipo Navegável
Figma: https://www.figma.com/proto/Z7UNRFNNiicKRAb41dhKxj/Projeto-Final-(Pexel)?node-id=17%3A7&scaling=scal
## Como Usar

Seu dispositivo precisa estar conectado à internet para a autenticação, e após o login as funcionalidades do app podem ser usadas também offline

- Crie uma nova conta usando seu e-mail zup e em seguida faça Login.
- Navegue pelo app usando o menu inferior.
- Tela Desafios: Os 4 desafios são atualizados diariamente, complete-os clicando na checkbox para ganhar pontos e subir de nível.
- Tela Bem Estar Zupper: Exibe uma foto motivacional que é atualizada diariamente; Exibe informações de Take a Break; Exibe botões clicáveis que redirecionam o usuário para os respectivos sites de benefícios sobre saúde e bem estar que a Zup oferece aos Zuppers.
- Tela Notas e Lembretes: Digite uma nota ou um lembrete e clique no botão 'Salvar'. As notas salvas aparecerão abaixo do botão 'Salvar', e podem ser excluídas clicando no ícone de lixeira.
- Menu superior: Em todas as telas existe um menu superior, ao ser clicado leva o usuário para a tela Perfil.
- Tela Perfil: Exibe o nome, e-mail do usuário e o botão 'Sair'. Ao clicar no botão 'Sair' o usuário será deslogado do app e retornará à tela de Login.
## Ficha Técnica

- 1.0 Arquitetura: MVVM;

- 1.1 IDE: Android Studio 2021.2.1.14;

- 1.2 API 21: Android 5.0 (Lollipop);

- 1.3 Linguagem de programação: Kotlin.

- 2.0 Banco de dados interno: Room Database;

- 2.1 Banco de dados externo: Firebase Realtime Database.

- 3.0 API RESTful: Pexels (https://www.pexels.com/pt-br/api/documentation/);

- 3.1 Biblioteca REST Client: Retrofit.

- 4.0 Serviço de autenticação: Firebase Authentication.
## Plugins

```kotlin
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-parcelize'
    id 'kotlin-kapt'
    id 'com.google.gms.google-services'
}
```
## Dependências - App Level

```kotlin
dependencies {
    //Lifecycle
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0'
    implementation 'androidx.annotation:annotation:1.4.0'
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.5.0'
    
    //Navigation
    implementation 'androidx.navigation:navigation-fragment-ktx:2.4.2'
    implementation 'androidx.navigation:navigation-ui-ktx:2.4.2'
    implementation 'androidx.navigation:navigation-fragment:2.5.1'
    
    //Network_Retrofit
    implementation 'com.google.code.gson:gson:2.9.0'
    implementation "com.squareup.retrofit2:converter-gson:2.6.0"
    implementation "com.squareup.retrofit2:retrofit:2.9.0"
    implementation "com.squareup.okhttp3:logging-interceptor:4.2.1"
    
    //Image_Loader
    implementation 'com.squareup.picasso:picasso:2.71828'
    
    //Firebase
    implementation platform('com.google.firebase:firebase-bom:30.2.0')
    implementation 'com.google.firebase:firebase-auth-ktx'
    implementation 'com.google.firebase:firebase-database-ktx'
    implementation 'com.google.firebase:firebase-database:19.2.1'
    implementation 'androidx.room:room-common:2.4.3'
    
    //Room Database
    implementation("androidx.room:room-runtime:2.4.2")
    kapt("androidx.room:room-compiler:2.4.2")
}
```


## Dependências - Project Level

```kotlin
buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath 'com.google.gms:google-services:4.3.13'
    }
}
```

## Suporte

Para suporte, envie um email para um dos colaboradores:

catarine.rodrigues@zup.com.br

gabriela.godoy@zup.com.br

jeniffer.sival@zup.com.br

victor.carvalho@zup.com.br

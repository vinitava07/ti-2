# Visão Geral do projeto.
## Qual é o tema e o nome do projeto?
Tema: Prompt de IA em redes sociais. 
Nome: PowerChat GPT.

## Qual é o problema tratado pelo projeto?
Reduziremos o tamanho da jornada de acesso para prompts de inteligência artificial, conectando-os à uma ferramenta que muitos usam no dia a dia: o whatsapp.

## Para qual público o projeto se destina?
Para entusiastas da plataforma ChatGPT.

## Quais foram as principais motivações para o desenvolvimento do projeto?
Acreditamos que o chatGPT é uma ferramenta que deverá ser cada vez mais utilizada, e ao inserí-la no whatsapp, transformamo-os em interface de conexão com essa, mas também com outras APIS de IA. 

## Qual é o objetivo do projeto?
Conectar o ChatGPT ao whatsapp, de maneira que um usuário possa ter uma chat inteligente no aplicativo que provavelmente já mais usa.

## Que impacto o projeto trará para seu público e para a sociedade?
Público: Facilitará o uso do ChatGPT.

## Qual é o diferencial/inovação do projeto em relação ao que já existe?
Não existem muitos concorrentes que disponibilizam isso, e os que fazem perdem em 2 aspectos:
- Não se conectam com outros serviços de AI, só com o ChatGPT
- Não tem outro produto para oferecer por trás, não gerando assim MOAT para os seus negócios.

## Como o projeto se sustentará? Quais são os mecanismos de monetização? 
- Freemium. Separado em tiers:
    - Grátis: até 10 perguntas.
    - Preemium: até 300 perguntas (19,90R$)
    - Enterprise: valor customizado.

## Qual é a jornada do usuário no sistema a ser desenvolvido?
- Acesso à landing page e cadastro na ferramenta, informando nome, email, telefone e se deseja a ferramenta para uso comercial ou coorp.
- Redirecionamento para o whatsapp, onde o usuário terá acesso ao chat com nosso número de telefone.
- Realização de perguntas e obtenção de resposta.
- Caso o usuário ultrapasse o limite de perguntas, geraremos um cupom automático no Stripe e enviaremos para ele, sugerindo o upsell.
- Caso o usuário acesse o número sem ter criado uma conta, o redirecionaremos para a página web.

## Quais são as principais entidades, atributos, relacionamento e restrições do banco de dados do projeto?
User, Question, Subscription e Plan. Mais informações são apresentadas em seguida.

- Restrições: 
    - nenhum por enquanto.

## Quais são os recursos de sistemas inteligentes e inteligência artificial a serem adotados no projeto?
- Integraremos com a API do OpenAI.

# Visão Geral dos requisitos
- User Story 1: Eu, como usuário, gostaria de me cadastrar no serviço, para poder acessar o ChatGPT no meu WhatsApp.
    Critérios de aceitação: 
    - Usuário deve informar nome, email e telefone para concluir cadastro.
    - Caso o usuário envie uma mensagem para o número sem ter feito sua inscrição antes, o serviço deve responder uma mensagem direcionando-o para o cadastro na plataforma web.
- User Story 2: Eu, como usuário, gostaria de fazer uma pergunta ao ChatGPT pelo whatsapp e ser respondido.
    Critério de aceitação:
    - Se conectar ao webhook do facebook e receber as mensagens de lá.
    - Responder novamente ao facebook usando a API de GraphQL deles.
- User Story 3: Eu, como business, gostaria de gerar um cupon de 15% de desconto para que o usuário assine o plano pago assim que ele estourar o limite gratuito.
    - Critérios de aceitação:  
    - Ao estourar o limite de mensagens de seu plano, usuário deve receber mensagem com link e cupon para conversão.
- User Story 4: Eu, como usuário, gostaria de pagar o plano preemium, para que eu possa fazer mais perguntas do que o limite gratuito.
    Critérios de aceitação:
    - Usuário deve poder comprar o plano preemium assim que sua assinatura for feita.
    - Cobrança deve oferecer disconto para valor anual.
- User Story 5: Eu, como business, gostaria de limitar o tamanho de resposta da API do ChatGPT para manter a operação lucrativa.
    Critérios de aceitação:
    - Resposta deve ter até 300 palavras. 
    - Resposta não deve informar que texto foi cortado.
- User Story 6: Eu, como desenvolvedor, gostaria de acompanhar que a solução está sempre online e em bom funcionamento. 
    Critérios  de aceitação:
    - Registrar falha de geração de resposta pela api da OpenAI
    - Monitorar saúde do serviço de 1 em 1 minuto.

# Descrição do Mini Mundo do banco de dados
@startuml
entity "User" as user {
 * number <<PK>>
 * email
 * name
}

entity "Plan" as plan {
 * id <<PK>>
 * name
 * monthly_prompt_limit
}

entity "Subscription" as subs {
 * id <<PK>>
 * user <<FK>>
 * plan <<FK>>
 * created_at
 * is_active
 * expiration_date
}

entity "Question" as question {
 * subscription <<FK>>
 * question
 * reply
 * created_at
 * id <<PK>> <<generated>>
}

user|o..o{ subs
plan|o..o{ subs
user|o..o{ question
subs|o..o{ question    
@enduml

# Descrição dos recursos inteligentes
Usaremos da API do ChatGPT para responder às perguntas feitas pelos usuários.

# Apresentação do projeto
- Gerar apresentação com obsidian.
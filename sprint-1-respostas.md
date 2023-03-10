## Qual é o tema e o nome do projeto?
Tema: Prompt de IA em redes sociais comuns. 
Nome: PowerChat GPT.

## Qual é o problema tratado pelo projeto?
Reduziremos o tamanho da jornada de acesso para prompts de inteligência artificial, conectando-os à uma ferramenta que muitos usam no dia a dia: o whatsapp.

## Para qual público o projeto se destina?
Para entusiastas da plataforma ChatGPT.

## Quais foram as principais motivações para o desenvolvimento do projeto?
Queremos construir uma solução topo de funíl para o negócio de um dos membros do grupo, de maneira a gerar leads e diminuir o custo de acquisição para outro produto tangencial.

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
 * user <<FK>>
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

- Restrições: 
    - nenhum por enquanto.

## Quais são os recursos de sistemas inteligentes e inteligência artificial a serem adotados no projeto?
- Integraremos com a API do OpenAI.
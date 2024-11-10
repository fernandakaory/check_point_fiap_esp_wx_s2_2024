# Relatório Algorítmo RLE - Grupo Abobrinha

## Seção 1 - Introdução
Este relatório visa detalhar o processo de desenvolvimento de um projeto em Java voltado para a compressão de dados de sequências de nucleotídeos, utilizando o método Run-Length Encoding (RLE). Nele, são descritas as principais decisões e implementações realizadas pelo grupo, assim como as etapas seguidas até a obtenção dos resultados finais. Com foco na compressão de dados genéticos, o projeto visa demonstrar como essa técnica pode otimizar o armazenamento e a manipulação de grandes conjuntos de dados biológicos.
## Seção 2 - Fundamentos
A compressão de dados é um recurso importante para o tratamento de grandes volumes de informações, especialmente em áreas relacionadas a biologia e genética, nas quais o armazenamento compacto de sequências genéticas reduz o consumo de espaço e melhora a eficiência do processamento. O método Run-Length Encoding (RLE) é uma técnica que comprime dados ao identificar e registrar padrões de repetições consecutivas, registrando a quantidade de ocorrências ao invés de cada caractere. Essa abordagem é particularmente útil para sequências genéticas, que comumente apresentam blocos de nucleotídeos repetidos.
### Estrutura do Código
As funções principais foram agrupadas em uma nova classe chamada `Compressor` e chamadas na classe principal `App`, com o intuito de organizar melhjor a estrutura do projeto. Abaixo serão aboradadas, brevemente, cada função:

**Geração da Sequência de Nucleotídeos:** O método `generateSequence()` solicita ao usuário a quantidade de nucleotídeos que deseja gerar. A sequência gerada é feita aleatoriamente, mas com uma probabilidade de 70% de um nucleotídeo ser repetido consecutivamente, o que torna a sequência mais parecida com sequências reais.

**Compressão da Sequência (RLE):** A função `compressRLE()` realiza a compressão da sequência usando o método RLE. Esse método detecta repetições consecutivas e as substitui por uma versão compacta (nucleotídeo seguido pela contagem de repetições). Essa compressão é especialmente eficaz em sequências com muitos caracteres repetidos.

**Cálculo de Frequências: O método countFrequency()** analisa a sequência para calcular a frequência de cada nucleotídeo (A, C, G, T), exibindo esses dados no resumo final.

**Leitura e Escrita de Arquivos:** O programa permite a leitura de sequências a partir de arquivos de texto e a gravação da sequência comprimida em um novo arquivo. Para isso, os métodos `inputReader()` e `outputWriter()` implementam operações de leitura e escrita de maneira eficiente, utilizando BufferedReader e BufferedWriter.

**Exibição do Resumo da Compressão:** O método `displaySummary()` exibe um relatório com as seguintes informações:

- Nome dos arquivos de entrada e saída.
- Sequência original e a sequência comprimida.
- Taxa de compressão calculada com base na redução do tamanho da sequência.
- Frequência de ocorrência dos nucleotídeos A, C, G e T.
## Seção 3 - Metodologia
O projeto foi estruturado em etapas bem definidas, com o intuito de construir uma ferramenta de compressão eficiente e organizada:

**Estrutura Inicial do Projeto:** Como primeiro passo, foi criado um fork do projeto base disponibilizado pelo professor no GitHub. Os primeiros commits incluíram as pastas e os arquivos de entrada e saída vazios, conforme orientado em sala de aula.

**Geração de Sequências de Nucleotídeos:** Atendendo a uma demanda em sala de aula, foi desenvolvido um código para gerar uma sequência de nucleotídeos com tamanho especificado pelo usuário, ainda que os detalhes dessa etapa tenham sido abordados anteriormente no Checkpoint 2.

**Leitura de Input e Escrita de Output:** Para as operações de leitura e escrita, inicialmente utilizamos métodos discutidos em aula. Posteriormente, diante das especificidades do projeto, optamos por alternativas que melhor atendiam nossas necessidades, conforme referências obtidas em um artigo da DevMedia, citado no código.

**Implementação do RLE:** Em seguida, avançamos para a implementação do algoritmo de compressão RLE, com base em um documento de apoio da Universidade de Campinas, que forneceu fundamentos e orientações para a programação do método.

**Contagem dos Nucleotídeos:** Para complementar a análise dos resultados, foi implementado um método de contagem da frequência de cada nucleotídeo na sequência, reutilizando parcialmente códigos desenvolvidos durante as aulas.

**Resumo da Operação e Formatação de String:** Na fase final, foi criado um resumo que exibe informações como a frequência de nucleotídeos e a taxa de compressão. Aplicamos técnicas de formatação de strings para garantir a clareza e organização dessas informações.

**Padronização do Projeto em Inglês:** Para manter a padronização do código, todas as partes previamente escritas em português foram traduzidas para o inglês, garantindo consistência com o restante do projeto.

**Estrutura do Código em Classes:** Por fim, foi criada a classe Compressor para organizar os métodos de compressão, deixando a classe principal focada apenas nas chamadas de métodos.
## Seção 4 - Experimentos
O desempenho da ferramenta foi avaliado por meio de testes em sequências de tamanhos variados e com diferentes padrões. A cada teste, variamos a quantidade de nucleotídeos gerados e observamos o comportamento do método RLE frente às variações de repetição e diversidade dos caracteres. Os experimentos foram realizados com dados de entrada personalizados e analisados em outputs distintos para validação dos resultados.
## Seção 5 - Resultados
Os testes indicaram que o método RLE foi eficaz na redução do tamanho das sequências, especialmente nas que apresentavam grandes blacos de nucleotídeos repetidos. No resumo final gerado pelo método displaySummary, foram destacadas informações relevantes, como:

Frequência de nucleotídeos (A, C, G e T), expressa em porcentagens e valores absolutos.
Taxa de compressão, representada pela redução no tamanho das sequências após a aplicação do RLE, resultando em arquivos de menor tamanho.
## Seção 6 - Conclusões
Após a codificação completa do projeto, realização de diferentes testes e análises dos resultados, foi possível concluir sua eficácia para a compressão de sequências de nucleotídeos. O algoritmo RLE se mostrou útil para uso com dados repetitivos, proporcionando uma redução significativa no tamanho dos dados. A padronização do código e a organização das classes contribuíram para tornar o projeto mais claro e funcional.
## Seção 7 - Referências
- Martino, J. "Compressão de Dados - Run-Length Encoding". Disponível em: https://www.dca.fee.unicamp.br/~martino/disciplinas/ea978/na4.pdf
- DevMedia. "Leitura e Escrita de Arquivos de Texto em Java". Disponível em: https://www.devmedia.com.br/amp/leitura-e-escrita-de-arquivos-de-texto-em-java/25529
INSERT INTO events (
    id, name, description, location, event_date, start_time, end_time,
    event_type, ticket_url, official_site_url, online_url,
    created_at, updated_at, category_id, organizer_id
) VALUES
      (
          gen_random_uuid(), 'DevSummit 2025',
          'A maior conferência de desenvolvedores da América Latina. Palestras, workshops e networking com os maiores nomes da indústria de tecnologia.',
          'Expo Center Norte, São Paulo, SP', '2025-08-20', '09:00:00', '18:00:00',
          'PRESENCIAL', 'https://sympla.com.br/devsummit2025', 'https://devsummit.com', null,
          NOW(), NOW(),
          (SELECT id FROM categories WHERE name = 'Tecnologia'),
          (SELECT id FROM users WHERE email = 'ana@gmail.com')
      ),
      (
          gen_random_uuid(), 'Indie Fest',
          'Festival de música independente com bandas locais e nacionais. Um dia inteiro de shows, food trucks e arte.',
          'Parque Ibirapuera, São Paulo, SP', '2025-09-15', '14:00:00', '23:00:00',
          'PRESENCIAL', 'https://ticketmaster.com/indiefest', 'https://indiefest.com.br', null,
          NOW(), NOW(),
          (SELECT id FROM categories WHERE name = 'Música'),
          (SELECT id FROM users WHERE email = 'fernando@gmail.com')
      ),
      (
          gen_random_uuid(), 'Global Game Jam Online',
          'Participe da maior maratona de desenvolvimento de jogos do mundo, totalmente online. Crie um jogo em 48 horas!',
          null, '2026-01-24', '18:00:00', '18:00:00',
          'ONLINE', null, 'https://globalgamejam.org', 'https://twitch.tv/globalgamejam',
          NOW(), NOW(),
          (SELECT id FROM categories WHERE name = 'Games'),
          (SELECT id FROM users WHERE email = 'roberto@gmail.com')
      ),
      (
          gen_random_uuid(), 'Workshop de Aquarela',
          'Aprenda as técnicas básicas de aquarela com a renomada artista Luiza. Material incluso. Vagas limitadas.',
          'Ateliê da Vila, Rua Harmonia, 789, Vila Madalena, SP', '2025-07-12', '10:00:00', '13:00:00',
          'PRESENCIAL', 'https://sympla.com.br/workshopaquarela', null, null,
          NOW(), NOW(),
          (SELECT id FROM categories WHERE name = 'Arte & Cultura'),
          (SELECT id FROM users WHERE email = 'luiza@gmail.com')
      ),
      (
          gen_random_uuid(), 'Startup Connect',
          'Evento de networking para fundadores de startups, investidores e entusiastas do ecossistema de inovação.',
          'Cubo Itaú, São Paulo, SP', '2025-10-05', '19:00:00', '22:00:00',
          'PRESENCIAL', null, 'https://cubo.network/startupconnect', null,
          NOW(), NOW(),
          (SELECT id FROM categories WHERE name = 'Negócios & Networking'),
          (SELECT id FROM users WHERE email = 'ana@gmail.com')
      ),
      (
          gen_random_uuid(), 'Final do Campeonato de E-Sports',
          'Assista à grande final do campeonato nacional de Valorant. Torça pelo seu time favorito!',
          'Arena E-Sports, Rio de Janeiro, RJ', '2025-11-22', '15:00:00', '21:00:00',
          'HIBRIDO', 'https://sympla.com.br/final-vct', 'https://vctbr.com', 'https://youtube.com/vctbr',
          NOW(), NOW(),
          (SELECT id FROM categories WHERE name = 'Esportes'),
          (SELECT id FROM users WHERE email = 'roberto@gmail.com')
      ),
      (
          gen_random_uuid(), 'Festival de Comida de Rua',
          'Os melhores food trucks da cidade reunidos em um só lugar. Venha provar sabores do mundo todo.',
          'Praça da Liberdade, Belo Horizonte, MG', '2025-08-30', '12:00:00', '22:00:00',
          'PRESENCIAL', null, null, null,
          NOW(), NOW(),
          (SELECT id FROM categories WHERE name = 'Comida & Bebida'),
          (SELECT id FROM users WHERE email = 'fernando@gmail.com')
      ),
      (
          gen_random_uuid(), 'Webinar: O Futuro da Inteligência Artificial',
          'Palestra online com Dr. Roberto sobre as tendências e o impacto da IA na sociedade e nos negócios.',
          null, '2025-09-10', '19:30:00', '21:00:00',
          'ONLINE', null, 'https://eventos.tech/webinar-ia', 'https://zoom.us/j/123456789',
          NOW(), NOW(),
          (SELECT id FROM categories WHERE name = 'Tecnologia'),
          (SELECT id FROM users WHERE email = 'roberto@gmail.com')
      ),
      (
          gen_random_uuid(), 'Aula de Yoga no Parque',
          'Comece seu final de semana com uma aula de yoga ao ar livre para todos os níveis. Traga seu tapete!',
          'Parque da Cidade, Brasília, DF', '2025-07-19', '09:00:00', '10:30:00',
          'PRESENCIAL', null, null, null,
          NOW(), NOW(),
          (SELECT id FROM categories WHERE name = 'Saúde & Bem-estar'),
          (SELECT id FROM users WHERE email = 'luiza@gmail.com')
      ),
      (
          gen_random_uuid(), 'Show Acústico - Ana Prado',
          'Uma noite íntima com as canções de Ana Prado em formato voz e violão. Show de abertura com artistas locais.',
          'Blue Note, Rio de Janeiro, RJ', '2025-12-05', '20:00:00', '22:30:00',
          'PRESENCIAL', 'https://tudus.com.br/anaprado', null, null,
          NOW(), NOW(),
          (SELECT id FROM categories WHERE name = 'Música'),
          (SELECT id FROM users WHERE email = 'ana@gmail.com')
      ),
      (
          gen_random_uuid(), 'Curso Intensivo de Oratória',
          'Perca o medo de falar em público. Curso prático de 4 horas com certificado de participação.',
          'Av. Paulista, 1842, São Paulo, SP', '2025-10-18', '09:00:00', '13:00:00',
          'HIBRIDO', 'https://sympla.com.br/cursooratoria', 'https://falarempublico.com', 'https://teams.microsoft.com/l/123',
          NOW(), NOW(),
          (SELECT id FROM categories WHERE name = 'Educação'),
          (SELECT id FROM users WHERE email = 'fernando@gmail.com')
      ),
      (
          gen_random_uuid(), 'Festa de Lançamento do Álbum',
          'Venha celebrar o lançamento do novo álbum da banda "Os Elétricos". DJ sets antes e depois do show.',
          'Circo Voador, Rio de Janeiro, RJ', '2026-02-20', '22:00:00', '04:00:00',
          'PRESENCIAL', 'https://sympla.com.br/festa-eletricos', null, null,
          NOW(), NOW(),
          (SELECT id FROM categories WHERE name = 'Festas & Vida Noturna'),
          (SELECT id FROM users WHERE email = 'fernando@gmail.com')
      );
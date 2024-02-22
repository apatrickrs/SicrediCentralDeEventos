package com.sicredi.teste.centraldeeventos.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.sicredi.teste.centraldeeventos.data.model.Event
import com.sicredi.teste.centraldeeventos.data.repositories.EventRepository
import com.sicredi.teste.centraldeeventos.domain.EventsViewModel
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

class EventRepositoryImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var eventsViewModel: EventsViewModel

    @Mock
    var repository: EventRepository = mock()

    private var mockEvents = listOf(
        Event(
            "1",
            emptyList(),
            1534784400000,
            "O Patas Dadas estará na Redenção, nesse domingo, com cães para adoção e produtos à venda!\n\nNa ocasião, teremos bottons, bloquinhos e camisetas!\n\nTraga seu Pet, os amigos e o chima, e venha aproveitar esse dia de sol com a gente e com alguns de nossos peludinhos - que estarão prontinhos para ganhar o ♥ de um humano bem legal pra chamar de seu. \n\nAceitaremos todos os tipos de doação:\n- guias e coleiras em bom estado\n- ração (as que mais precisamos no momento são sênior e filhote)\n- roupinhas \n- cobertas \n- remédios dentro do prazo de validade",
            "http://lproweb.procempa.com.br/pmpa/prefpoa/seda_news/usu_img/Papel%20de%20Parede.png",
            -51.2146267,
            -30.0392981,
            29.99,
            "Feira de adoção de animais na Redenção"
        ),
        Event(
            "2",
            emptyList(),
            1534784400000,
            "Vamos ajudar !!\n\nSe você tem na sua casa roupas que estão em bom estado de uso e não sabemos que fazer, coloque aqui na nossa página sua cidade e sua doação, com certeza poderá ajudar outras pessoas que estão passando por problemas econômicos no momento!!\n\nAjudar não faz mal a ninguém!!!\n",
            "http://fm103.com.br/wp-content/uploads/2017/07/campanha-do-agasalho-balneario-camboriu-2016.jpg",
            -51.2148497,
            -30.037878,
            59.99,
            "Doação de roupas"
        ),
        Event(
            "3",
            emptyList(),
            1534784400000,
            "Atenção! Para nosso brique ser o mais organizado possível, leia as regras e cumpra-as:\n* Ao publicar seus livros, evite criar álbuns (não há necessidade de remetê-los a nenhum álbum);\n* A publicação deverá conter o valor desejado;\n* É preferível publicar uma foto do livro em questão a fim de mostrar o estado em que se encontra;\n* Respeite a ordem da fila;\n* Horário e local de encontro devem ser combinados inbox;\n* Caso não possa comparecer, avise seu comprador/vendedor previamente;\n* Caso seu comprador desista, comente o post com \"disponível\";\n* Não se esqueça de apagar a publicação se o livro já foi vendido, ou ao menos comente \"vendido\" para que as administradoras possam apagá-la;\n* Evite discussões e respeite o próximo;\n",
            "http://www.fernaogaivota.com.br/documents/10179/1665610/feira-troca-de-livros.jpg",
            -51.2148497,
            -30.037878,
            19.99,
            "Feira de Troca de Livros"
        ),
        Event(
            "4",
            emptyList(),
            1534784400000,
            "Toda quarta-feira, das 17h às 22h, encontre a feira mais charmosa de produtos frescos, naturais e orgânicos no estacionamento do Shopping. Sintonizado com a tendência crescente de busca pela alimentação saudável, consumo consciente e qualidade de vida. \n\nAs barracas terão grande variedade de produtos, como o shiitake cultivado em Ibiporã há mais de 20 anos, um sucesso na mesa dos que não abrem mão do saudável cogumelo na dieta. Ou os laticínios orgânicos da Estância Baobá, famosos pelo qualidade e modo de fabricação sustentável na vizinha Jaguapitã. Também estarão na feira as conhecidas compotas e patês tradicionais da Pousada Marabú, de Rolândia.\n\nA feira do Catuaí é uma nova opção de compras de produtos que não são facilmente encontrados no varejo tradicional, além de ótima pedida para o descanso de final de tarde em família e entre amigos. E com o diferencial de ser noturna, facilitando a vida dos consumidores que poderão sair do trabalho e ir direto para a “Vila Verde”, onde será possível degustar delícias saudáveis nos bistrôs, ouvir música ao vivo, levar as crianças para a diversão em uma estação de brinquedos e relaxar ao ar livre.\n\nEXPOSITORES DA VILA VERDE CATUAÍ\n\nCraft Hamburgueria\nNido Pastíficio\nSabor e Saúde\nTerra Planta\nEmpório da Papinha\nEmpório Sabor da Serra\nBoleria Dom Leonardi\nCoisas que te ajudam a viver\nPatês da Marisa\nMarabú\nBaobá\nAkko\nCervejaria Amadeus\n12 Tribos\nParr Kitchen\nHorta Fazenda São Virgílio\nHorta Chácara Santo Antonio\nSur Empanadas\nFit & Sweet\nSK e T Cogumelos\nDos Quintana\n\nLocal: Estacionamento (entrada principal do Catuaí Shopping Londrina)\n\n\nAcesso à Feira gratuito.",
            "https://i2.wp.com/assentopublico.com.br/wp-content/uploads/2017/07/Feira-de-alimentos-org%C3%A2nicos-naturais-e-artesanais-ganha-um-novo-espa%C3%A7o-em-Ribeir%C3%A3o.jpg",
            -51.2148497,
            -30.037878,
            19.0,
            "Feira de Produtos Naturais e Orgânicos"
        ),
        Event(
            "5",
            emptyList(),
            1534784400000,
            "Uma maratona de programaÃ§Ã£o, na qual estudantes e profissionais das Ã¡reas de DESIGN, PROGRAMAÃ‡ÃƒO e MARKETING se reunirÃ£o para criar projetos com impacto social positivo atravÃ©s dos pilares de EducaÃ§Ã£o Financeira e Colaborar para Transformar.\n\nO evento serÃ¡ realizado por duas empresas que sÃ£o movidas pela transformaÃ§Ã£o: o Woop Sicredi e a Smile Flame.\n\n// Pra ficar esperto:\n\n- 31/08, 01 e 02 de Setembro, na PUCRS;\n- 34 horas de duraÃ§Ã£o;\n- Atividades direcionadas para criaÃ§Ã£o de soluÃ§Ãµes digitais de impacto social;\n- Mentorias para apoiar o desenvolvimento das soluÃ§Ãµes;\n- ConteÃºdo de apoio; \n- AlimentaÃ§Ã£o inclusa;\n\n// ProgramaÃ§Ã£o\n\nSexta-feira - 31/08 - 19h Ã¡s 22h\nSÃ¡bado e Domingo - 01 e 02/09 - 9h do dia 01/09 atÃ© as 18h do dia 02/09.\n\n// RealizaÃ§Ã£o\nWoop Sicredi\nSmile Flame\n\nMaiores infos em: https://www.hackathonsocial.com.br/\nTÃ¡ com dÃºvida? Manda um e-mail pra gabriel@smileflame.com\n\nEaÃ­, ta tÃ£o animado quanto nÃ³s? LetÂ´s hack!",
            "https://static.wixstatic.com/media/579ac9_81e9766eaa2741a284e7a7f729429022~mv2.png",
            -51.2148497,
            -51.2148497,
            59.99,
            "Hackathon Social Woop Sicredi"
        )
    )

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        eventsViewModel = EventsViewModel()
    }

    @Test
    fun `test must getEvents`() {
        val liveData = MutableLiveData<List<Event>>()
        liveData.value = mockEvents

        doReturn(liveData).`when`(repository).getEvents()

        repository.getEvents()
        eventsViewModel.getEvents()

        liveData.observeForever(object : Observer<List<Event>> {
            override fun onChanged(value: List<Event>) {
                assertEquals(value, mockEvents)
                liveData.removeObserver(this)
            }
        })

        verify(repository).getEvents()
    }

    @Test
    fun `test must getEventById`() {
        val liveData = MutableLiveData<List<Event>>()
        liveData.value = mockEvents

        doReturn(liveData).`when`(repository).getEventById(2)

        repository.getEventById(2)
        eventsViewModel.getEventById(2)

        liveData.observeForever(object : Observer<List<Event>> {
            override fun onChanged(value: List<Event>) {
                assertEquals(value[1].id, mockEvents[1].id)
                liveData.removeObserver(this)
            }
        })
        verify(repository).getEventById(2)
    }
}
import com.sng.PersonTask
import static java.util.Map.entry

class PersonSpec extends BaseSpec {

    static men = false
    static women = true

    static PersonTask.Person getPerson(String surname, String name, String patronymic) {
        return new PersonTask.Person(name, patronymic, surname)
    }

    static personsFull = List.of(
        getPerson("Алексеев", "Вадим", "Эдуардович"),
        getPerson("Алексеев", "Илья", "Михайлович"),
        getPerson("Архипов", "Максим", "Владимирович"),
        getPerson("Белоусов", "Сергей", "Станиславович"),
        getPerson("Блезэ", "Ричард", "Николаевич"),
        getPerson("Бодров", "Георгий", "Александрович"),
        getPerson("Васильев", "Георгий", "Алексеевич"),
        getPerson("Воротынский", "Максим", "Олегович"),
        getPerson("Галанцов", "Константин", "Андреевич"),
        getPerson("Глазунов", "Егор", "Алексеевич"),
        getPerson("Дмитриев", "Никита", "Константинович"),
        getPerson("Донов", "Роман", "Алексеевич"),
        getPerson("Евстафьева", "Наталья", "Михайловна"),
        getPerson("Ерышев", "Владимир", "Анатольевич"),
        getPerson("Зарецкий", "Никита", "Евгеньевич"),
        getPerson("Заерко", "Марина", "Витальевна"),
        getPerson("Злобина", "Полина", "Сергеевна"),
        getPerson("Лобанова", "Мария", "Владимировна"),
        getPerson("Малышкина", "Лада", "Евгеньевна"),
        getPerson("Мирзоев", "Руслан", "Намигович"),
        getPerson("Мухин", "Алексей", "Николаевич"),
        getPerson("Никитин", "Андрей", "Юрьевич"),
        getPerson("Оберемок", "Илья", "Сергеевич"),
        getPerson("Орлов", "Максим", "Евгеньевич"),
        getPerson("Петров", "Константин", "Эдуардович"),
        getPerson("Петухова", "Дарья", "Андреевна"),
        getPerson("Полещук", "Евгений", "Олегович"),
        getPerson("Поляничко", "Константин", "Александрович"),
        getPerson("Попов", "Никита", "Антонович"),
        getPerson("Рублев", "Дмитрий", "Витальевич"),
        getPerson("Сабинин", "Александр", "Александрович"),
        getPerson("Скачков", "Максим", "Сергеевич"),
        getPerson("Сторожев", "Дмитрий", "Анатольевич"),
        getPerson("Тихонова", "Мария", "Владимировна"),
        getPerson("Тюрин", "Марк", "Олегович"),
        getPerson("Хлобыстов", "Иван", "Юрьевич"),
        getPerson("Храмцов", "Арсений", "Николаевич"),
        getPerson("Щепин", "Егор", "Васильевич"),
        getPerson("Эверстова", "Анастасия", "Константиновна"),
    )

    def "0: sort by name and surname"(){
        given:
        def persons = List.of(
                getPerson("Иванов", "Максим", "Анатольевич"),
                getPerson("Иванова", "Мария", "Анатольевна"),
                getPerson("Иванова", "Анна", "Анатольевна"),
                getPerson("Иванов", "Андрей", "Иванович"),
                getPerson("Аксёнов", "Олег", "Андреевич"),
                getPerson("Аксёнова", "Ангелина", "Андреевна"),
                getPerson("Аксаков", "Антон", "Андреевич"),
                getPerson("Тихонова", "Мария", "Владимировна"),
                getPerson("Блезэ", "Ричард", "Николаевич"),
        )
        def expected = List.of(
                getPerson("Аксёнова", "Ангелина", "Андреевна"),
                getPerson("Иванов", "Андрей", "Иванович"),
                getPerson("Иванова", "Анна", "Анатольевна"),
                getPerson("Аксаков", "Антон", "Андреевич"),
                getPerson("Иванов", "Максим", "Анатольевич"),
                getPerson("Иванова", "Мария", "Анатольевна"),
                getPerson("Тихонова", "Мария", "Владимировна"),
                getPerson("Аксёнов", "Олег", "Андреевич"),
                getPerson("Блезэ", "Ричард", "Николаевич"),
        )

        when:
        var sorted = PersonTask.sortByNameSurname(persons)

        then:
        sorted == expected
    }

    def "1: find popular name"(){
        when:
        var winner = PersonTask.getMostPopularName(personsFull)

        then:
        winner == "Максим"
    }

    def "2: find top popular names"(){
        when:
        var winners = PersonTask.getTopNPopularNames(personsFull, 3)

        then:
        winners == List.of("Максим", "Константин", "Никита")
    }

    def "3: find men and women num"(){
        given:
        def womenNum = 8
        def menNum = personsFull.size() - womenNum

        when:
        var menAndWomenNumber = PersonTask.getMenAndWomenNumber(personsFull)

        then:
        menAndWomenNumber.first() == menNum
        menAndWomenNumber.second() == womenNum
    }

    def "4: get hypothetical relatives"() {
        given:
        def persons = List.of(
            getPerson("Иванов", "Максим", "Анатольевич"),
            getPerson("Иванова", "Мария", "Анатольевна"),
            getPerson("Иванова", "Анна", "Анатольевна"),
            getPerson("Иванов", "Андрей", "Иванович"),
            getPerson("Аксёнов", "Олег", "Андреевич"),
            getPerson("Аксёнова", "Ангелина", "Андреевна"),
            getPerson("Аксаков", "Антон", "Андреевич"),
            getPerson("Тихонова", "Мария", "Владимировна"),
            getPerson("Блезэ", "Ричард", "Николаевич"),
        )

        when:
        def result = PersonTask.getHypotheticalRelatives(persons)

        then:
        result ==  Map.of(
        "Анатольевич", List.of(
                            getPerson("Иванова", "Анна", "Анатольевна"),
                            getPerson("Иванов", "Максим", "Анатольевич"),
                            getPerson("Иванова", "Мария", "Анатольевна"),
                         ),
        "Андреевич",  List.of(
                            getPerson("Аксёнова", "Ангелина", "Андреевна"),
                            getPerson("Аксёнов", "Олег", "Андреевич"),
                        )
        )
    }

    def "5: find duplicates"() {
        given:
        def withDuplicates = List.of(
            getPerson("Зарецкий", "Никита", "Евгеньевич"),
            getPerson("Петухова", "Дарья", "Андреевна"),
            getPerson("Петухова", "Дарья", "Андреевна"),
            getPerson("Щепин", "Егор", "Васильевич"),
            getPerson("Щепин", "Егор", "Васильевич"),
            getPerson("Эверстова", "Анастасия", "Константиновна"),
        )

        when:
        def result = PersonTask.getDuplicates(withDuplicates)

        then:
        result ==  List.of(
            getPerson("Петухова", "Дарья", "Андреевна"),
            getPerson("Щепин", "Егор", "Васильевич"),
        )
    }

    def "6: find unique names"(){
        when:
        var uniqueNames = PersonTask.getUniqueNames(List.of(
            getPerson("Зарецкий", "Никита", "Евгеньевич"),
            getPerson("Петухова", "Дарья", "Андреевна"),
            getPerson("Петухова", "Дарья", "Андреевна"),
        ))

        then:
        uniqueNames == List.of("Дарья", "Никита")
    }

    def "6.1: find unique names in full list"(){
        when:
        var uniqueNames = PersonTask.getUniqueNames(personsFull)

        then:
        uniqueNames == List.of( "Александр", "Алексей", "Анастасия", "Андрей", "Арсений", "Вадим",
                "Владимир", "Георгий", "Дарья", "Дмитрий", "Евгений", "Егор", "Иван", "Илья", "Константин",
                "Лада", "Максим", "Марина", "Мария", "Марк", "Наталья", "Никита", "Полина", "Ричард", "Роман",
                "Руслан", "Сергей")
    }

    def "7: find name to persons list"(){
        when:
        var result = PersonTask.getNameToPersons(List.of(
            getPerson("Ерышев", "Владимир", "Анатольевич"),
            getPerson("Зарецкий", "Никита", "Евгеньевич"),
            getPerson("Дмитриев", "Никита", "Константинович"),
        ))

        then:
        result == Map.of(
            "Владимир", List.of(
                getPerson("Ерышев", "Владимир", "Анатольевич"),
            ),
            "Никита",  List.of(
                getPerson("Дмитриев", "Никита", "Константинович"),
                getPerson("Зарецкий", "Никита", "Евгеньевич"),
            )
        )
    }

    def "7.1: find name to persons full list"() {
        when:
        var result = PersonTask.getNameToPersons(personsFull)

        then:
        result == Map.ofEntries(
            entry("Руслан", List.of(
                    getPerson("Мирзоев", "Руслан", "Намигович")
            )),
            entry("Дарья", List.of(
                    getPerson("Петухова", "Дарья", "Андреевна")
            )),
            entry("Вадим", List.of(
                    getPerson("Алексеев", "Вадим", "Эдуардович")
            )),
            entry("Илья", List.of(
                    getPerson("Алексеев", "Илья", "Михайлович"),
                    getPerson("Оберемок", "Илья", "Сергеевич")
            )),
            entry("Иван", List.of(
                    getPerson("Хлобыстов", "Иван", "Юрьевич")
            )),
            entry("Евгений", List.of(
                    getPerson("Полещук", "Евгений", "Олегович")
            )),
            entry("Анастасия", List.of(
                    getPerson("Эверстова", "Анастасия", "Константиновна")
            )),
            entry("Лада", List.of(
                    getPerson("Малышкина", "Лада", "Евгеньевна")
            )),
            entry("Марк", List.of(
                    getPerson("Тюрин", "Марк", "Олегович")
            )),
            entry("Полина", List.of(
                    getPerson("Злобина", "Полина", "Сергеевна")
            )),
            entry("Мария", List.of(
                    getPerson("Лобанова", "Мария", "Владимировна"),
                    getPerson("Тихонова", "Мария", "Владимировна")
            )),
            entry("Ричард", List.of(
                    getPerson("Блезэ", "Ричард", "Николаевич")
            )),
            entry("Константин", List.of(
                    getPerson("Галанцов", "Константин", "Андреевич"),
                    getPerson("Петров", "Константин", "Эдуардович"),
                    getPerson("Поляничко", "Константин", "Александрович")
            )),
            entry("Никита", List.of(
                    getPerson("Дмитриев", "Никита", "Константинович"),
                    getPerson("Зарецкий", "Никита", "Евгеньевич"),
                    getPerson("Попов", "Никита", "Антонович")
            )),
            entry("Егор", List.of(
                    getPerson("Глазунов", "Егор", "Алексеевич"),
                    getPerson("Щепин", "Егор", "Васильевич")
            )),
            entry("Дмитрий", List.of(
                    getPerson("Рублев", "Дмитрий", "Витальевич"),
                    getPerson("Сторожев", "Дмитрий", "Анатольевич")
            )),
            entry("Георгий", List.of(
                    getPerson("Бодров", "Георгий", "Александрович"),
                    getPerson("Васильев", "Георгий", "Алексеевич")
            )),
            entry("Арсений", List.of(
                    getPerson("Храмцов", "Арсений", "Николаевич")
            )),
            entry("Сергей", List.of(
                    getPerson("Белоусов", "Сергей", "Станиславович")
            )),
            entry("Александр", List.of(
                    getPerson("Сабинин", "Александр", "Александрович")
            )),
            entry("Алексей", List.of(
                    getPerson("Мухин", "Алексей", "Николаевич")
            )),
            entry("Марина", List.of(
                    getPerson("Заерко", "Марина", "Витальевна")
            )),
            entry("Роман", List.of(
                    getPerson("Донов", "Роман", "Алексеевич")
            )),
            entry("Максим", List.of(
                    getPerson("Архипов", "Максим", "Владимирович"),
                    getPerson("Воротынский", "Максим", "Олегович"),
                    getPerson("Орлов", "Максим", "Евгеньевич"),
                    getPerson("Скачков", "Максим", "Сергеевич")
            )),
            entry("Наталья", List.of(
                    getPerson("Евстафьева", "Наталья", "Михайловна")
            )),
            entry("Андрей", List.of(
                    getPerson("Никитин", "Андрей", "Юрьевич")
            )),
            entry("Владимир", List.of(
                    getPerson("Ерышев", "Владимир", "Анатольевич")
            ))
        )
    }

    def "8: get name parts length median"(){
        given:
        def persons = List.of(
                getPerson("Зарецкий", "Никита", "Евгеньевич"),
                getPerson("Донов", "Роман", "Алексеевич"),
                getPerson("Щепин", "Егор", "Васильевич"),
                getPerson("Злобина", "Полина", "Сергеевна"),
                getPerson("Смирнова", "Василиса", "Николаевна"),
                getPerson("Эверстова", "Анастасия", "Константиновна"),
                getPerson("Евстафьева", "Наталья", "Михайловна"),
        )
        def expMenSurnameMed = 5
        def expMenNameMed = 5
        def expMenPatrMed = 10
        def expectedMenStats = new Integer[]{expMenSurnameMed, expMenNameMed, expMenPatrMed}

        def expWomenSurnameMed = ("Смирнова".size() + "Эверстова".size())/2
        def expWomenNameMed = ("Василиса".size() + "Наталья".size())/2
        def expWomenPatrMed = 10
        def expectedWomenStats = new Integer[]{expWomenSurnameMed, expWomenNameMed, expWomenPatrMed}


        when:
        var result = PersonTask.getNamePartsLengthMedian(persons)

        then:
        result == Map.of(
            men, expectedMenStats,
            women, expectedWomenStats,
        )
    }

    def "8.1: get name parts length median full persons list"(){
        given:
        def expectedMenStats = new Integer[]{7, 6, 10}
        def expectedWomenStats = new Integer[]{8, 5, 10}


        when:
        var result = PersonTask.getNamePartsLengthMedian(personsFull)

        then:
        result == Map.of(
            men, expectedMenStats,
            women, expectedWomenStats,
        )
    }

    def "9: get name parts length median"(){
        given:
        def persons = List.of(
            getPerson("Зарецкий", "Никита", "Евгеньевич"),
            getPerson("Донов", "Роман", "Алексеевич"),
            getPerson("Эверстова", "Анастасия", "Константиновна"),
            getPerson("Евстафьева", "Наталья", "Михайловна"),
        )
        def expMenTop = Map.of(
            'а' as char, (2.0d/"НикитаРоман".size()).round(2),
            'и' as char, (2.0d/"НикитаРоман".size()).round(2),
        )
        def expWomenTop = Map.of(
            'а' as char, (5.0d/"АнастасияНаталья".size()).round(2),
            'н' as char, (2.0d/"АнастасияНаталья".size()).round(2),
        )
        def expectedStats = Map.of(
            men, expMenTop,
            women, expWomenTop,
        )

        when:
        var result = PersonTask.getCharPopularity(persons, 2)

        then:
        result == expectedStats
    }

    def "9.1: get char popularity full persons list"(){
        given:

        def expMenTop = Map.of(
            'а' as char, 0.11d,
            'и' as char, 0.14d,
        )
        def expWomenTop = Map.of(
            'а' as char, 0.28d,
            'и' as char, 0.11d,
        )
        def expectedStats = Map.of(
            men, expMenTop,
            women, expWomenTop,
        )

        when:
        var result = PersonTask.getCharPopularity(personsFull, 2)

        then:
        result == expectedStats
    }

}

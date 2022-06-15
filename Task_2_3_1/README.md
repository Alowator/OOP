### Итак, что мы имеем:
Предыдущая идея сделать ядро независимым (на callback'ах) оказалась не очень удачной, 
так как надо разруливать многопоточность.

Теперь схема MVC реализована в полном виде, именно Controller дергает все обновления модели.
Делает контроллер это все из потока UI с помощью `Timeline`. Никаких проблем нет, так как у нас 
один обычный (даже не асинхронный) поток.

# `Application.java`
Точка входа.

# `contoller`
Тут живет JavaFX контроллер, главный - `Main.java`, тут обрабатываем все UI последствия...
Окно настроек открывается отсюда же. За настройки ответственность несет отдельны контроллер `Settings.java`

# `core`
Модель нашей игры. Куча классов примитивной логики. Остановимся на самых важных аспектах:
* `View` - интерфейс, который вью собственно и должен реализовывать
* в `core/frame` лежит `record`, который мы отдаем нашему `View` для отрисовки, это состояние игры.

## `core/entities/Enemies.java`
Главный герой данной лабы. 
Тут лежит вся логика поведения вражеских змеек, и сами эти же змейки.

Змейки ищут пути до еды используя алгоритм поиска максимального потока в графе
(Алгоритм Форда-Фалкерсона в самом простом виде). 

Что мы делаем - берем сток (абстрактная ячейка поля), разрешаем 
движение от нее до каждой из головы змеек, далее берем исток, его мы соединяем со всеми клетками еды,
движение по полю в графе строится как ожидается - из каждой клетки можно попасть во все соседние,
за исключением стен, вражеских змеек и головы змейки игрока.

Как итог имеем нереально жесткий интеллект змеек, каждый шаг они перестраивают этот граф, 
ищут в нем максимальный поток, и поворачивают соответственно найденому потоку.

Эти змеек можно победить, как минимум потому что в данной реализации змейка игрока имеет преимущество -
она поворачивает первая. Также змейки не умеют смотреть на несколько шагов вперед, поэтому могут залезать в тупик.
Однако они опасаются (воспринимают как стены) другие змейки, соответственно играют сообща и не откусывают куски
друг друга.
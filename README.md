__**Techniques adapted: **__
    Mvvm + Clean Architecture
    MVP + Clean Architecture
    (refer to TramActivity.kt, the mvvm or mvp can be selected here)
    Dagger2
    kotlin

__**Code explain **__
    Code structure:
    Clean Architecture: 3 modules: data, domain, presentation

    Presentation Module:
    MVVM pattern:
    M: data+domain layer
       ui/model: define the data UI needed
                 eg: add another data item predictedLeftTime in TramItemViewData.kt, to show tram will arrive "in * minutes"
       ui/mapper is the place convert data to UI needed.
                 eg:  data format conversion is done in TramItemViewDataMapper.kt

    VM: ui/mvvm,
        ->TramViewModel: make api call, and update livedata

    V: ui/mvvm,Activity, Fragment, and xml
        ->react use action(press refresh button) and delege to TramViewModel
        ->observe livedata in TramViewModel
        ->update UI passively


    MVP pattern:
    See folder ui/mvp

    Dagger2:
    di/module: provide Repository, ViewModel, others classes that constructor have @inject annotation
    di/component: delcare injection places:  fragment and others



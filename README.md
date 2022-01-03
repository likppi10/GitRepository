# KurlyGit

## 주요 이슈

<details> 
  <summary>1. 메인 화면 구성 </summary> 
  
   #### 1-1. 검색 창 구현
      (1) EditTextView로 검색창을 구현
   
      (2) 검색 버튼을 구현
   
   #### 1-2. 목록 리스트 구현

      (1) 리싸이클러뷰로 구현

      (2) 임의의 값으로 리싸이클러 뷰의 아이템이 잘 출력되는지 확인
		(fork, star, title, subtitle)
</details>

<details> 
  <summary> 2. API 연결 </summary> 
  
   #### 2-1. 레트로핏 사용하기 위한 환경설정 : 

    (1) ApplicationClass 생성
  
    (2) RetrofitUtil 생성
  
    (3) RepositoryAPI 생성
  
  #### 2-2. 레트로핏에서 사용할 쿼리 및 콜백 등 환경설정 : 

    (1) RepositoryService 생성

    (2) BaseCallback 생성

  #### 2-3. 레파지토리 데이터를 담을 객체 생성 : 

    (1) Repository 생성
    
    (2) Repositories 생성

  #### 2-4. 특정 단어(Kotlin)로 검색 시 결과가 잘 나오는지 확인

  #### 2-5. 검색한 단어가 잘 전달 되는지 확인

  #### 2-6. 검색한 단어로 검색되는지 확인

  #### 2-7. 검색된 결과 리스트에 출력

  #### 2-8. 한 번 검색 후 다시 검색했을 때 결과 재확인

  #### 2-9. 검색 결과 개수 출력

  #### 2-10. 검색 결과 추가 로딩

    (1) 결과가 없을 때

    (1-1) 어댑터에는 이미 불러온 리스트가 있고, 더 불러올게 없을 경우

    (1-2) 원래 없었던 경우 (처음 검색했을 때, 한 번 검색한 상태에서 또 검색했을 때) 모두의 경우

    > (1)의 경우 한번이라도 이 곳에 오게되면, addOnScrollListener는 컨트롤 할 수 없더라도,
    불필요한 레트로핏 통신으로 가는 것은 막아줘야합니다. (stopThisIsEnd를 true로 바꿔줍니다.)

    (2) 결과가 있다.

    (2-1) 첫번째로 검색할 때 > 검색

    (2-2) 한번 검색을 하고, 다른 것을 검색할 때 > 기존의 것을 지우고, 새로 검색

    (2-3) 첫번째 검색 후 추가로 볼 때

  #### 2-11. UI 교정

  (1) UI가 디스플레이 영역을 넘어서거나 어색한 부분들을 교정합니다.
  
</details>

<details> 
  <summary> 3. Coroutine, DI, mvvm 패턴 </summary> 
  
  #### 3-1. 코루틴 적용

    (1) retrofit의 Callback 방식 > 코루틴의 runCatching 방식

  #### 3-2. DI (Koin)

    (1) repository 모듈 생성

    (2) viewModel 모듈 생성

    (3) network 모듈 생성

  #### 3-3. viewmodel 생성 및 dataBinding

    (1) MainViewModel을 생성

    (2) DiffUtil을 사용하여 분기별로 다른 notify가 아닌 일괄적인 submit

    (3) 리사이클러뷰 데이터 바인딩

    (4) 메인 액티비티 검색 결과 없을 시 텍스트, 검색 결과 수 데이터 바인딩
</details>


<details> 
  <summary> 4. 프로젝트 구조 변경 </summary> 
  
 #### 4-1. 패키지 재구성

    (1) di는 따로 구성

    (2) ui(presentation) - data(data) - entity(domain)의 패키지로 나눔

  #### 4-2. 엔티티와 모델 분리

    (1) 모델 및 엔티티 생성

    (2) 엔티티 entity 패키지로 이동

  #### 4-3. Mapper 생성

    (1) data패키지에 Mapper 생성
</details>

<details> 
  <summary> 5. 주석 및 코멘트 </summary> 
</details>

<details> 
  <summary> 6. 에러 및 이슈	 </summary> 
  
 #### 6-1. 엔티티, 모델의 null 처리 관련 에러 이슈 : [이슈 해결 링크 ](https://github.com/likppi10/KurlyGit/issues/32)

</details>

<details> 
  <summary> 7. 마무리	 </summary> 
  
 #### (1) 검색 결과의 publish/private 여부 표시 형식 수정

</details>

## 프로젝트 소개

위의 이슈 단계에서 **[[2. API 연결]](https://github.com/likppi10/KurlyGit/tree/2_API_Connect)** 와 **[[7. 마무리]](https://github.com/likppi10/KurlyGit/tree/7_Finish)** 가 실질적인 두 가지 완료 시점입니다. 

(클릭 후 해당 branch에서 클론하시면, 다른 시점의 코드들을 받아 보실 수 있습니다.)

**-두 가지 시점은 구현된 코드는 다소 다르지만, 결과는 같습니다. 
한 가지 차이점이 있다면, [2. API 연결] 시점에서는 디스플레이의 회전이 작동하면, 데이터가 휘발되는 반면, [7. 마무리] 시점에서는 liveData가 사용되어 
회전이 작동해도 데이터가 휘발되어 보여지지는 않는 것입니다.-**

**[[2. API 연결]](https://github.com/likppi10/KurlyGit/tree/2_API_Connect)** 의 완료 시점에서는  

리사이클러뷰에 notifyDataSetChanged()와 notifyItemRangeInserted()를 이용하여 검색이 이루어지는 경우의 분기에 따라 결과를 **notify 하는 방법**이 다릅니다. 
이슈와 코드 주석에서 보시면 알 수 있듯이 갈라지는 다양한 상황과 로딩이 사라질 때 예상할 수 없는 예외를 고려하여 구현했습니다. 

하지만 **기능 구현에만 집중하여 비동기 작업이나, 패턴들의 적용은 최소한으로 구현**했습니다. 

**[[7. 마무리]](https://github.com/likppi10/KurlyGit/tree/7_Finish)** 의 완료 시점에서는  

비동기 작업을 위한 코루틴 적용, mvvm 패턴 적용 그리고 네트워크 통신을 위한 레트로핏이나, viewModel 등을 모듈화 하여 koin으로 DI 했습니다. 
mvvm 패턴에서는 mvvm 패턴의 시초인 마이크로소프트의 .NET에서 말하는 "View와 ViewModel의 어떠한 명시적인 커플링 없는 구현"에는 실패했지만, 
mvvm화와 데이터 바인딩을 통한 메인 액티비티의 역할을 줄이고 세분화 하여 유지 보수의 효율이라는 장점은 챙길 수 있는 코드 구조로 구현했습니다. 

또한 이런 과정에서 notifyXXX()를 사용하는 대신 diffUtil을 사용하여 일괄적인 submit으로 어댑터의 MutableList의 변화에 대한 효율을 증가시켰습니다. 
그리고 프로젝트 구조를 바꾸면서 모델과 엔티티를 분리해서 맵핑 시켜주는 레파지토리 패턴을 적용하여, 최대한 백엔드의 변화를 프론트엔드에서 알 수 없게 하여
, 프로젝트를 **변화에 이전 완료 시점보다 잘 적응할 수 있는 구조로 구현**했습니다.  


## 구현 화면

#### - 검색 결과 화면
<img src="https://user-images.githubusercontent.com/54509842/147877111-a21abb5c-b576-4bd6-8a31-ec6818ce3a3b.jpg" width="30%"></img>

#### - 검색 로딩 화면
<img src="https://user-images.githubusercontent.com/54509842/147877115-b9cc1177-9602-4da9-a3da-20973421fd9f.jpg" width="30%"></img>

#### - 검색 결과 화면 (결과가 없을 때)
<img src="https://user-images.githubusercontent.com/54509842/147877117-20822824-b508-45bc-8b3c-bb66d715e04b.jpg" width="30%"></img>


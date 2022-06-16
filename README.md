# 채팅 프로그램

`Java Swing` 환경의 텍스트 채팅 애플리케이션으로, `Socket` 통신과 `Multi Thread` 프로그래밍으로 구현한 프로그



## 목차

* [개발 환경 및 사용 기술](#개발-환경-및-사용-기술)
* [실행화면과 기능설명](#실행화면과-기능설명)
* [구현 방식](#구현-방식)
* [서버 애플리케이션](#서버-애플리케이션)
* [클라이언트 애플리케이션](#클라이언트-애플리케이션)

 

## 개발 환경 및 사용 기술

* 개발 환경
  * macOS Monterey 12.4
  * IntelliJ IDEA
  * Java 11
* 사용 기술
  * Java Network Socket
  * AWT, Swing





## 실행화면과 기능설명

* 로그인 화면

  * 사용자 아이디와 이름을 입력하는 화면
  * `사용자 아이디`: 프로그램 내부에서 사용자를 식별하는 데이터
  * `이름`: 사용자 목록과 채팅을 입력한 사람을 표시하는 데이터

  <br>
  <p align="center">
     <img src="/image/login-frame.png" width="600" align="center">
  </p>
  <br>


* 로비 화면 
  * 로비 채팅 (모든 사용자 채팅 가능)
  * 채팅 프로그램에 접속한 모든 사용자 리스트 출력
  * 채팅방 목록 출력

<br>
  <p align="center">
     <img src="/image/lobby-frame.png" width="800" align="center">
  </p>
<br>


* 채팅방 생성 화면

  * 채팅방 이름 입력

  * `확인` 버튼을 누르면 입력한 문자열을 바탕으로 새로운 채팅방 생성

    * 새로운 채팅 화면 출력

     <br>
       <p align="center">
          <img src="/image/create-chat-frame.png" width="600" align="center">
       </p>
     <br>
      

    * 생성된 채팅방 목록들은 로비에서 확인 가능


      <br>
        <p align="center">
           <img src="/image/chat-room-list.png" width="600" align="center">
        </p>
      <br>
    

    * 채팅방 목록에서 `채팅방 이름 클릭` 하면, 해당 채팅 화면 출력


     <br>
       <p align="center">
          <img src="/image/click-chat-room-name.png" width="600" align="center">
       </p>
     <br>    


* 채팅방 화면

  * 해당 채팅방에 접속한 사용자 목록 출력


   <br>
     <p align="center">
        <img src="/image/chat-room-chat.png" width="800" align="center">
     </p>
   <br>

  

  * `나가기 버튼 클릭` 하면 채팅창에 퇴장 메시지 출력되고, 사용자 목록 갱신

  
   <br>
     <p align="center">
        <img src="/image/exit-btn-click.png" width="600" align="center">
     </p>
   <br>
  

  * 모든 사용자가 채팅방에서 나간 경우, 채팅방 이름을 로비의 채팅 목록에서 제거

    * `채팅방 이름1` 목록에서 제거

<br>
  <p align="center">
     <img src="/image/remove-chat-room-name.png" width="600" align="center">
  </p>
<br>
    

  * 이미 열려있는 채팅방을 다시 들어가려 할 경우 오류 팝업 출력


<br>
  <p align="center">
     <img src="/image/chat-room-reopen-error.png" width="600" align="center">
  </p>
<br>



## 구현 방식

* 서버와 클라이언트는 `Socket` 으로 통신

* 클라이언트

  * `Swing` 을 이용해 화면을 구성
  * `Thread` 를 이용해 자신의 `Socket InputSream` 을 지속적으로 읽어와서 서버에서 보낸 메시지를 수신

* 서버 

  * 각 클라이언트에 대한 `Socket` 을 `Multi Thread` 환경으로 관리
  * `Multi Thread` 를 이용해 모든 사용자들의  `Socket InputSream` 을 지속적으로 확인
  * 클라이언트가 보낸 메시지를 바탕으로 서버에 저장된 자료구조를 처리하고, 응답을 전송

* 클라이언트와 서버는 문자열 메시지를 주고 받으며, 주고 받는 자료구조는 형식에 따라 모두 문자열로 치환

  * 양쪽에서 동일한 `Request Response DTO` 를 가지고 있지만 클라이언트의 경우 `Request DTO ➔ String` 으로 변경해서 송신하고, 

    서버는 이를 받아 파싱하여 `String ➔ Request DTO` 를 생성해 사용

    서버도 마찬가지로 클라이언트로 메시지를 보낼 경우 `Response DTO` 객체를 생성해 전달할 내용으로 인스턴스를 초기화하고

     `Response DTO ➔ String` 으로 변경해 클라이언트에게 전송

    클라이언트는 이를 받아 파싱하여 `String ➔ Response DTO` 를 생성해 사용

    

  * `Request DTO` : 클라이언트가 서버에 보내는 메시지 (객체 내용을 문자열로 생성)

  * `Response DTO`: 서버가 사용자에게 보내는 메시지 (객체 내용을 문자열로 생성)

    * 흐름도


     <br>
       <p align="center">
          <img src="/image/string-object-parse.png" width="600" align="center">
       </p>
     <br>


    * 파싱 데이터 테이블


      `DtoType:data` 형식의 문자열로 메시지 송수신

      `Request` 의 전송 주체는 `Client`,  `Response` 의 전송 주체는 `Server`

      

      | 데이터 종류                | 문자열                                                       | 파싱 객체                                |
      | -------------------------- | ------------------------------------------------------------ | ---------------------------------------- |
      | 로그인 정보                | `LOGIN:userId,userName`                                      | `LoginRequest `                          |
      | 초기 데이터 정보           | `LOGIN:chatRoomName|chatRoomName.. +userId,userName|userId,userName..` | `InitDataResponse`                       |
      | 채팅방 생성 정보           | `CREATE_CHAT:chatRoomName,userId`                            | `CreateChatRoomRequest`                  |
      | 채팅방 입장 정보           | `ENTER_CHAT:chatRoomName,userId`                             | `EnterChatRequest`                       |
      | 채팅방 퇴장 정보           | `EXIT_CHAT:chatRoomName,userId`                              | `ExitChatRequest`                        |
      | 채팅 메시지 정보           | `MESSAGE:messageType,chatRoomName,userName,message`          | `MessageRequest` <br />`MessageResponse` |
      | 채팅방 <br />사용자 리스트 | `USER_LIST:chatRoomName|userId,userName..`                   | `UserListResponse`                       |
      | 채팅방 리스트              | `CHAT_ROOM_LIST:userName,userName..` 또는 `CHAT_ROOM_LIST:empty` | `ChatRoomListResponse`                   |

      

    

    * `DtoType` 에 따라 메시지 송수신부에서 분기 처리
    

      ```java
      class enum DtoType {
        	INIT, // 로그인 후 초기 데이터 전송 관련 메시지 (사용자 리스트, 채팅방 리스트)
      
          LOGIN, // 로그인 메시지 (사용자 생성, 리스트에 추가)
          CREATE_CHAT, // 새로운 채팅방 생성 메시지
          ENTER_CHAT, EXIT_CHAT, // 채팅방 입장, 퇴장 메시지
          MESSAGE, // 채팅 메시지
      
          USER_LIST, CHAT_ROOM_LIST, // 사용자 리스트, 채팅방 리스트 메시지
      }
      ```

      ```java
      class DTO {
        	DtoType type;
      
          public DTO(DtoType type) {
              this.type = type;
          }
      
          public DtoType getType() {
              return type;
          }
      
          @Override
          public String toString() {
              return type + ":";
          }
      }
      ```

    

    * 예시 코드

      ```java
      /* Client Request */
      
      // Client Application
      public class LoginRequest extends DTO {
      
          String id;
      
          String name;
      
          public LoginRequest(String id, String name) {
              super(DtoType.LOGIN);
      
              this.id = id;
              this.name = name;
          }
      
        	// 데이터 보낼 때 객체 내용을 바탕으로 문자열로 바꿔서 전송
          @Override
          public String toString() {
              return super.toString() + id + "," + name;
          }
      }
      
      // Server Application
      public class LoginRequest {
      
          String id;
      
          String name;
      
        	// 스트링 메시지로 수신한 내용을 객체로 파싱해서 사용
          public LoginRequest(String message) {
              String[] value = message.split(",");
              id = value[0];
              name = value[1];
          }
      
          public String getId() {
              return id;
          }
      
          public String getName() {
              return name;
          }
      }		
      ```

      ```java
      /* Server Response */
      
      // Server Application
      public class ChatRoomListResponse extends DTO {
      
          List<ChatRoom> chatRooms;
      
          public ChatRoomListResponse(List<ChatRoom> chatRooms) {
              super(DtoType.CHAT_ROOM_LIST);
      
              this.chatRooms = chatRooms;
          }
      
        	// Object to String
          @Override
          public String toString() {
              String str = super.toString();
      
              for (ChatRoom chatRoom : chatRooms) {
                  str += chatRoom.getName() + ",";
              }
              return (chatRooms.size() > 0) ? str.substring(0, str.length() - 1) : str + "empty";
          }
      }
      
      // Client Application
      public class ChatRoomListResponse {
      
          List<ChatRoom> chatRooms = new ArrayList<>();
      
        	// String to Object
          public ChatRoomListResponse(String message) {
              if (!message.equals("empty")) {
                  String[] names = message.split(",");
                  for(String name : names) {
                      chatRooms.add(new ChatRoom(name));
                  }
              }
          }
      
          public List<ChatRoom> getChatRooms() {
              return chatRooms;
          }
      }
      ```



## 서버 애플리케이션

* 클래스 멤버

  ```java
  class Application {
    
    public static List<Sockets> sockets; // 클라이언트 소켓 리스트
  
    ChatService chatService; // 채팅 데이터 처리 인스턴스 (ServerThread 인스턴스 생성 시 파라미터로 전달)
  
    ChatDao chatDao; // 채팅 데이터 관리 인스턴스 (ChatService 인스턴스 생성 시 파라미터로 전달)
    
  }
  
  ➔ Application 인스턴스는 main 함수 실행 시 한 번만 생성하므로, 애플리케이션에서 사용하는 인스턴스들을 생성해주는 역할
    
  ➔ Application 에서 클라이언트들의 Socket 연결을 대기하며 통신을 위한 소켓을 생성하므로 Socket List 도 함께 관리
    
  
  
  class ServerThread {
    
    Socket socket; // 클라이언트 소켓
  
  	ChatService chatService;
    
  }
  ➔ 각 클라이언트마다 ServerThread 인스턴스를 생성하여 스레드를 동작시켜 메시지 수신
  
    
  class User {
    
    String id; // 아이디
  
    String name; // 이름
  
    Socket socket; // 자신의 소켓 (클라이언트 소켓)
    
  }
  ➔ 사용자 클래스는 자신의 소켓 정보를 저장
  
  ➔ 사용자 인스턴스를 채팅방 사용자 리스트에 추가/삭제하며 메시지 송신 관리
  
    
  class ChatRoom {
    
    String name; // 채팅명
  
  	List<User> users; // 채팅방 내의 사용자 리스트
    
  }
  ➔ 채팅방 입장/퇴장에 따라 사용자 리스트에 User 인스턴스를 삽입 또는 삭제
    
  ➔ 각 채팅방에 관리되고있는 사용자 리스트를 바탕으로 서버에서 메시지 송신
  
  ➔ User 인스턴스 안에 Socket 정보가 있으므로 개별적인 채팅방 처리가 가능
    
    
    
  class ChatService {
    
    ChatDao chatDao;
    
  }
  ➔ DAO 에서 제공하는 메서드를 이용해, 채팅 프로그램에서 사용하는 데이터를 가져와서 처리
    
  
  class ChatDao {
    
    List<User> users; // 접속 중인 모든 사용자 리스트
  
    List<ChatRoom> chatRooms;
  
    ChatRoom lobby; // 로비 채팅방 정보 (로비를 계속 거쳐 다니니 로비 정보 저장)
  
    String LOBBY_CHAT_NAME = "Lobby";
    
  }
  ➔ ChatService 인스턴스에서 요청하는 채팅 데이터를 제공
    
  ➔ 다른 채팅방에 들어가 있어도 로비 채팅방은 계속 갱신하기 위해 lobby 로 따로 관리
  
  ```

  

* 애플리케이션 구조

  * 클래스 다이어그램

    
<br>
  <p align="center">
     <img src="/image/server-class-flow.png" width="1000" align="center">
  </p>
<br>
    

  * 인스턴스 다이어그램

    * 클라이언트 소켓 연결


      <br>
        <p align="center">
           <img src="/image/client-socket-connect.png" width="600" align="center">
        </p>
      <br>


    * 사용자 정보 생성 (아이디, 이름 입력 후)


      <br>
        <p align="center">
           <img src="/image/create-user.png" width="600" align="center">
        </p>
      <br>

    

    * 새로운 채팅방 생성

      <br>
        <p align="center">
           <img src="/image/create-chat-room.png" width="600" align="center">
        </p>
      <br>

      

      



## 클라이언트 애플리케이션

* 클래스 멤버

  ```java
  class Application {
    
      public static Socket socket;
  
      public static MessageSender sender;
  
      public static MessageReceiver receiver;
  
      public static LobbyFrame lobbyFrame;
  
      public static User me;
  
      public static List<User> users = new ArrayList<>(); // 현재 접속 중인 모든 사용자 리스트
  
      public static List<ChatRoom> chatRooms = new ArrayList<>(); 
  
    	// 채팅방 프레임 관리, 메시지 왔을 때 어떤 채팅방 레이블을 갱신해야 하는지 확인
    	// [key] 채팅방 이름
      public static Map<String, ChatPanel> chatPanelMap = new HashMap<>();
  
      public static Map<String, ChatRoomUserListPanel> chatRoomUserListPanelMap = new HashMap<>();
  
      public static final String LOBBY_CHAT_NAME = "Lobby"; // 로비 채팅방 이름
  
  }
  ➔ 서버와 소켓 연결 후 소켓 정보를 저장하고 관리
    
  ➔ 주기적으로 소켓 메시지를 읽기 위한 스레드 생성
  
    
    
  class MessageReader {
    
    Socket socket;
    
  }
  
  class MessageSender {
    
    Socekt socket;
    
  }
  ```

  

* 화면 프레임

<br>
  <p align="center">
     <img src="/image/client-view-frame.png" width="1000" align="center">
  </p>
<br>




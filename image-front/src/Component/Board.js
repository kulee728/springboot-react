import React, { useState, useEffect, useRef } from "react";
import axios from "axios";
import isEqual from "lodash/isEqual";

const Board = ()=> {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [files, setFiles] = useState([]);
  const [posts, setPosts] = useState([]);

  const getPosts = () => {
    axios.get("http://localhost:9007/posts").then((response) => {
      setPosts(response.data);
    });
    console.log("posts : ", posts);
  };

  //useEffect(getPosts,[posts]);

  function useCustomHook(obj) {
    const prevObjRef = useRef();
    useEffect(() => {
      if (!isEqual(prevObjRef.current, obj)) {
        console.log("obj가 바뀌었을 때만 이 console.log가 실행됩니다.");
        getPosts();
        prevObjRef.current = obj;
      }
    }, [obj]);
  }
  

  const serverSubmission = async () => {
    const formData = new FormData();
    Array.from(files).forEach((file) => {
      formData.append("files", file);
    });
    formData.append("title", title);
    formData.append("content", content);

    const res = await axios.post("/gallery/upload", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
        //전송할 데이터에 파일이 포함되어있음을 명시해준다.
      },
    });
    alert("자바로 이미지 전송했습니다.");
    setFiles([]);
    setContent("");
    setTitle("");
  };

  useCustomHook(posts);

  return (
    <div className="App">
      <div className="form-container">
        <table>
          <tbody>
            <tr>
              <td>
                <label className="input-label">제목 : </label>
              </td>
              <td>
                <input
                  type="text"
                  onChange={(e) => setTitle(e.target.value)}
                  value={title}
                />
              </td>
            </tr>
            <tr>
              <td>
                <label className="input-label">내용 : </label>
              </td>
              <td>
                <textarea
                  onChange={(e) => setContent(e.target.value)}
                  value={content}
                />
              </td>
            </tr>
            <tr>
              <td>
                <label htmlFor="a" className="input-label">이미지선택 : </label>
              </td>
              <td>
              <button onClick={serverSubmission}>이미지 업로드 버튼</button>
                <input
                  multiple
                  type="file"
                  onChange={(e) => setFiles(e.target.files)}
                  className="img-input"
                  id="a"
                />
              </td>
            </tr>
            <tr>
              <td>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div>
        {posts.map((post) => (
          <div key={post.id} className="post">
            <h2>
              {post.title} : {post.createdAt}
            </h2>
            <p>{post.content}</p>
            <div className="images">
            {
              /*
              post.files && post.files.map(file=>(
                <img key={file} src={file}/>
              )) >> 배열로 주어진 경우 
              하단에 images/${image} 구문은 Spring boot의 webConfig 코드와 연결된다. 
              */
              post.imageUrl.split(",").map((image) => (
                <img src={`http://localhost:9007/images/${image}`} />
              ))
            }
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Board;

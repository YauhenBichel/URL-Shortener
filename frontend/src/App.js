import {useEffect, useRef, useState} from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css';

const useMountedRef = () => {
    const mountedRef = useRef(false);

    useEffect(() => {
        setTimeout(() => {
            mountedRef.current = true;
        });
    }, []);

    return mountedRef;
};

function App() {
    const mountedRef  = useMountedRef();
    const [originalURL, setOriginalURL] = useState("");
    const [currentURL, setCurrentURL] = useState("");
    const [shortURL, setShortURL] = useState("");

    const generateShortUrl = () => {
        console.log("URL: ", originalURL);
        fetch('http://localhost:8080/url-shortener', {
            method: 'post',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                "originalUrl": originalURL
            })
        }).then((response) => response.json())
            .then(jsonResponse => {
                console.log("response: ", jsonResponse);
                setShortURL(window.location.href + jsonResponse.shortUrl);
            }).catch(err => {
            console.log("err: ", err);
        });
    }

    useEffect(() => {
        if (mountedRef.current) {
            return generateShortUrl();
        }
    }, [originalURL]);

    return (
      <div className="app">
          <div className="container position-absolute top-50 start-50 translate-middle">
              <div className="row">
                  <div className="input-group mb-3">
                      <input id="txtOriginalURL" type="text" className="form-control" placeholder="URL"
                             aria-label="Long URL" aria-describedby="btnGenerate"
                            onChange={e => setCurrentURL(e.target.value)}/>
                      <button className="btn btn-outline-secondary" type="button"
                              id="btnGenerate" onClick={(e) => {
                          e.preventDefault();
                          setOriginalURL(currentURL);
                          generateShortUrl();
                      }}>Generate</button>
                  </div>
              </div>
              <div className="row text-center">
                  <div className="mb-3">
                    <span>Short URL: </span>{shortURL}<span></span>
                  </div>
              </div>
          </div>

      </div>
  );
}

export default App;

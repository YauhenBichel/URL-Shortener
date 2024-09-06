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
    const [shortHash, setShortHash] = useState("");

    const generateShortUrl = () => {
        console.log("URL: ", originalURL);
        fetch('http://localhost:8080/api/url-shortener', {
            method: 'post',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                "originalUrl": originalURL
            })
        }).then((response) => response.json())
            .then(jsonResponse => {
                console.log("response: ", jsonResponse);
                console.log("response: short url: ", jsonResponse.shortUrl);
                if(jsonResponse && jsonResponse.shortUrl) {
                    setShortHash(jsonResponse.shortUrl);
                    setShortURL(window.location.host + '/' + jsonResponse.shortUrl);
                }
            }).catch(err => {
            console.log("err: ", err);
        });
    }

    useEffect(() => {

        const parts = window.location.href.split("http://localhost:3001");
        console.log(parts);
        if(parts.length > 1 && parts[1].matchAll(["^//a-zA-Z0-9"])) {
            const shortHash = parts[1].substring(1);
            console.log("short url");
            console.log("short hash: ", shortHash);
            fetch('http://localhost:8080/api/url-shortener/' + shortHash, {
                method: 'get',
                headers: {'Content-Type': 'application/json'},
            }).then((response) => response.json())
                .then(jsonResponse => {
                    console.log("response: ", jsonResponse);
                    console.log("response: original url: ", jsonResponse.originalUrl);
                    window.location.href = jsonResponse.originalUrl;
                }).catch(err => {
                console.log("err: ", err);
            });
            return;
        }

        if (mountedRef.current) {
            return generateShortUrl();
        }
    });

    const onDirectToOriginalUrl = (e) => {
        e.preventDefault();
        console.log("URL: ", originalURL);
        fetch('http://localhost:8080/api/url-shortener/' + shortHash, {
            method: 'get',
            headers: {'Content-Type': 'application/json'},
        }).then((response) => response.json())
            .then(jsonResponse => {
                console.log("response: ", jsonResponse);
                console.log("response: original url: ", jsonResponse.originalUrl);
                window.location.href = jsonResponse.originalUrl;
            }).catch(err => {
            console.log("err: ", err);
        });
    }

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
                    <a href={shortURL} onClick={onDirectToOriginalUrl}>{shortURL}</a>
                  </div>
              </div>
          </div>
      </div>
  );
}

export default App;

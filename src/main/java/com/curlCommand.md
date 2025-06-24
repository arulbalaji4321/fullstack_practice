CURL COMMAND TUTORIAL

---

### 1.

```bash
curl http://localhost:7070/total
```

* **What it does:**
  Sends an HTTP GET request to your API’s `/total` endpoint.
* **Purpose:**
  Fetches the total profit so far from your server.
* **Response you got:**

  ```json
  {"totalProfit":12000.0}
  ```

  This means the total sum of all purchased products recorded so far is ₹12,000.

---

### 2.

```bash
curl http://localhost:7070/turl -X POST http://localhost:7070/purchase -H "Content-Type: application/json" -d '{"product":"Camera Drone","price":12000}'
```

* **What you tried to do:**
  Looks like you tried to send a POST request to `/purchase` to add a new product "Camera Drone" priced at 12000.

* **Why it’s wrong / problematic:**
  The syntax is incorrect and malformed. You are mixing a GET request to `/turl` with `-X POST http://localhost:7070/purchase`, which doesn’t make sense for `curl`.
  The command has extra URL (`http://localhost:7070/purchase`) placed incorrectly after `-X POST`, which should only be followed by the HTTP method, not another URL.

* **Correct form:**

  ```bash
  curl -X POST http://localhost:7070/purchase -H "Content-Type: application/json" -d '{"product":"Camera Drone","price":12000}'
  ```

* This correctly sends a POST request to `/purchase` endpoint with a JSON body specifying the product and price.

---


### Summary of key curl options used:

* `curl [URL]` — by default, sends a GET request to the URL.

* `-X POST` — explicitly sets HTTP method to POST (used to send data).

* `-H "Content-Type: application/json"` — adds HTTP header specifying the request body is JSON.

* `-d '{"product":"Camera Drone","price":12000}'` — the JSON data to send in the body of the POST request.

---

If you want to test adding a new purchase and then check total, this sequence is correct:

```bash
curl -X POST http://localhost:7070/purchase -H "Content-Type: application/json" -d '{"product":"Camera Drone","price":12000}'
curl http://localhost:7070/total
```

This will record the new product and then return the updated total profit.

---

If you want, I can help you build some shell scripts or alias commands to make testing easier! Just say the word.

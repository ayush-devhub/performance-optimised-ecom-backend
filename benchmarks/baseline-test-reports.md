# Get Products
| Metric | Run 1 | Run 2 | Run 3 |
| --- | --- | --- | --- |
| **RPS (Throughput)** | 194.4 | 195.6 | 195.1 |
| **Avg Latency** | 12.4 ms | 10.6 ms | 9.2 ms |
| **Median Latency** | 7.21 ms | 9.61 ms | 6.09 ms |
| **p95 Latency** | 30.1 ms | 22.8 ms | 23.9 ms |
| **Max Latency** | 305 ms | 62 ms | 254 ms |
| **Errors** | 0% | 0% | 0% |

# Add to Cart 
| Metric | Run 1 | Run 2 | Change |
| --- | --- | --- | --- |
| **Throughput (RPS)** | 87.85/s | 55.98/s | **↓ ~36% lower throughput** |
| **Avg latency** | 66 ms | 385 ms | **↑ ~6× slower** |
| **p95 latency** | 145 ms | 615 ms | **↑ ~4× slower** |
| **Max latency** | 417 ms | 1112 ms | **↑ ~2.5× slower** |
| **Errors** | 0% | 0% | good |


# Checkout
| Metric | Run 1 | Run 2 | Δ Change |
| --- | --- | --- | --- |
| **Requests/sec** | 2.19 | 1.65 | ↓ 25% worse |
| **Avg latency** | 6.7 seconds | 9.3 seconds | ↓ ~38% slower |
| **p95 latency** | 8.6 sec | 11 sec | ↓ ~28% slower |
| **Max latency** | 9.1 sec | 11.4 sec | ↓ ~25% slower |
| **VUs dropped to** | 9 | 5 | **Low concurrency due to saturation** |
# Kubernetes Deployment

- [Kubernetes Deployment](#kubernetes-deployment)
  - [Code Deployment Local](#code-deployment-local)
  - [Code Deployment Remote](#code-deployment-remote)
  - [Notes](#notes)

## Code Deployment Local

1. Make sure Kubernetes is running
2. Launch All files using `kubectl apply -f .`
3. Update the Server URL to `localhost:80` in Frontend's environment file

## Code Deployment Remote

1. Go to Kubernetes Depoyment Code directory
2. Run `kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v0.40.2/deploy/static/provider/cloud/deploy.yaml`
3. Run `kubernetes apply -f .`
4. Check Google Cloud Console -> Services
   1. Look for a load balancer service
   2. Copy the Extenal Load Balancer URL
   3. Run it in Browser
   4. Wait for Sometime for services to start (15 min atleast)
5. Go to services -> Create Ingress (This step requires a NodePort service - Take a note if modifying K8s)
   1. Create Ingress
   2. Specify Path as default
   3. Specify Service as zuul-srv
   4. Create
6. Run the Client with External Load balancer IP

## Notes

- Sometimes Application may appear to crash aain and again, mostly this is due to low resource allocation
- Starting a new Deployment/ Updating requires some free CPU, so we need to manually delete an old deployment Pod
- Check all the service running status at Google Kubernetes Engine Dashboard

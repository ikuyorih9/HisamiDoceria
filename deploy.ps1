param (
    [string]$AWS_REGION = "us-east-1",
    [string]$AWS_ACCOUNT_ID = "<seu-id-conta-aws>",
    [string]$BACKEND_REPO = "backend-hisami",
    [string]$FRONTEND_REPO = "frontend-hisami",
    [string]$VERSION = "latest",
    [ValidateSet("all", "backend", "frontend")]
    [string]$Target = "all"
)

# Caminhos dos Dockerfiles
$BACKEND_PATH = "backend/hisami"
$FRONTEND_PATH = "frontend/hisami"

Write-Host "===== Construindo imagens Docker ====="

if ($Target -eq "all" -or $Target -eq "backend") {
    Write-Host ">>> Backend"
    docker build -t ${BACKEND_REPO}:${VERSION} $BACKEND_PATH
}

if ($Target -eq "all" -or $Target -eq "frontend") {
    Write-Host ">>> Frontend"
    docker build -t ${FRONTEND_REPO}:${VERSION} $FRONTEND_PATH
}

Write-Host "===== Fazendo login no ECR ====="
aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com"

Write-Host "===== Tagueando imagens ====="

if ($Target -eq "all" -or $Target -eq "backend") {
    docker tag ${BACKEND_REPO}:${VERSION} "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${BACKEND_REPO}:${VERSION}"
}

if ($Target -eq "all" -or $Target -eq "frontend") {
    docker tag ${FRONTEND_REPO}:${VERSION} "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${FRONTEND_REPO}:${VERSION}"
}

Write-Host "===== Fazendo push para o ECR ====="

if ($Target -eq "all" -or $Target -eq "backend") {
    docker push "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${BACKEND_REPO}:${VERSION}"
}

if ($Target -eq "all" -or $Target -eq "frontend") {
    docker push "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${FRONTEND_REPO}:${VERSION}"
}

Write-Host "===== Deploy finalizado! ====="

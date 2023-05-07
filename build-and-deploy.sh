#!/bin/bash

CURRENT_DIR=$(pwd)

print_title() {
    echo "----------------------------------------"
    echo "        Building and Deploying Project        "
    echo "----------------------------------------"
    echo
}

print_section() {
    echo
    echo "----------------------------------------"
    echo "        $1"
    echo "----------------------------------------"
    echo
}

print_success() {
    local message=$1
    if [[ -n $ZSH_VERSION ]]; then
        print -P "%F{green}[SUCCESS]%f $message"
    else
        echo -e "\e[32m[SUCCESS]\e[0m $message"
    fi
    echo
}

print_failure() {
    local message=$1
    if [[ -n $ZSH_VERSION ]]; then
        print -P "%F{red}[FAILURE]%f $message"
    else
        echo -e "\e[31m[FAILURE]%f $message"
    fi
    echo
}

print_title

cd backend || {
    print_failure "Failed to navigate to the 'backend' folder."
    exit 1
}

print_section "Building Backend"
echo "Executing 'mvn clean package -DskipTests'..."
mvn clean package -DskipTests || {
    print_failure "Failed to build the backend."
    exit 1
}
print_success "Backend built successfully."

cd "$CURRENT_DIR" || {
    print_failure "Failed to navigate back to the previous directory."
    exit 1
}

if ! command -v docker-compose >/dev/null 2>&1; then
    print_failure "docker-compose is not installed. Please install docker-compose and try again."
    exit 1
fi

print_section "Building Docker Images"
echo "Executing 'docker-compose build'..."
docker-compose build || {
    print_failure "Failed to build Docker images."
    exit 1
}
print_success "Docker images built successfully."

print_section "Deploying Project"
echo "Executing 'docker-compose up -d'..."
docker-compose up -d || {
    print_failure "Failed to deploy the project."
    exit 1
}
print_success "Project deployed successfully."

echo
echo "----------------------------------------"
echo "     Script completed successfully!     "
echo "----------------------------------------"
echo
echo "██╗███╗   ██╗███████╗██████╗  █████╗ ████████╗██████╗  █████╗  ██████╗██╗  ██╗███████╗██████╗ ";
echo "██║████╗  ██║██╔════╝██╔══██╗██╔══██╗╚══██╔══╝██╔══██╗██╔══██╗██╔════╝██║ ██╔╝██╔════╝██╔══██╗";
echo "██║██╔██╗ ██║█████╗  ██████╔╝███████║   ██║   ██████╔╝███████║██║     █████╔╝ █████╗  ██████╔╝";
echo "██║██║╚██╗██║██╔══╝  ██╔══██╗██╔══██║   ██║   ██╔══██╗██╔══██║██║     ██╔═██╗ ██╔══╝  ██╔══██╗";
echo "██║██║ ╚████║██║     ██║  ██║██║  ██║   ██║   ██║  ██║██║  ██║╚██████╗██║  ██╗███████╗██║  ██║";
echo "╚═╝╚═╝  ╚═══╝╚═╝     ╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝";
echo "                                                                                              ";                                                          
echo -e "You can access the frontend at: 🚀 \e[34mhttp://localhost:5173/\e[0m"

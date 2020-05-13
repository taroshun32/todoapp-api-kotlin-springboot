#!/bin/sh
set -eu

# git setting
git config --global user.name github-actions
git config --global user.email noreply@github.com

export GIT_BRANCH="$(git symbolic-ref HEAD --short 2>/dev/null)"
if [ "$GIT_BRANCH" = "" ]; then
  GIT_BRANCH="$(git branch -a --contains HEAD | sed -n 2p | awk '{ printf $1 }')"
  export GIT_BRANCH=${GIT_BRANCH#remotes/origin/}
fi

git remote set-url origin https://taroshun32:${GITHUB_TOKEN}@github.com/${GITHUB_REPOSITORY}.git
git checkout $GIT_BRANCH

# SchemaSpy
docker run -v "$PWD/schema:/output" --net="host" schemaspy/schemaspy:snapshot -t mysql -host localhost:3306 -db todo -u root -p password -connprops useSSL\\=false -s todo

# ignore no diff
set +e

git add .
git commit -m "update schema"
git push origin $GIT_BRANCH

echo 'finish SchemaSpy'

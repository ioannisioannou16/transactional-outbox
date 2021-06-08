#!/bin/sh

topic_arn=$(awslocal sns create-topic --name test_topic --output text)
echo "Topic ARN: $topic_arn"

queue_arn=$(awslocal sqs create-queue --queue-name test_queue --output text)
echo "Queue ARN: $queue_arn"

subscription_arn=$(awslocal sns subscribe \
    --topic-arn "$topic_arn" \
    --protocol sqs \
    --notification-endpoint "$queue_arn" \
    --output text)

echo "Subscription ARN: $subscription_arn"

